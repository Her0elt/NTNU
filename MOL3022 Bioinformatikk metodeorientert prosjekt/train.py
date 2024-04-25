import os

import keras.callbacks
import numpy as np
import pandas as pd
import tensorflow as tf
from keras import backend as K
from keras.losses import CategoricalCrossentropy
# from keras.utils import to_categorical
# from keras.utils import pad_sequences
from matplotlib import pyplot as plt
from sklearn.model_selection import train_test_split

from model import create_lstm_model, create_transformer_model
from preprocessing import get_data, preprocess
from utils import save_tokenizer


def acc(y_true, y_pred):
    '''
    This method calculates acc after masking padding (class = 0)
    :param y_true: True value of y. One-hot vector
    :param y_pred: Predicted value
    :return:
    '''
    y = tf.argmax(y_true, axis=-1)
    y_ = tf.argmax(y_pred, axis=-1)
    mask = tf.greater(y, 0)
    return K.cast(K.equal(tf.boolean_mask(y, mask), tf.boolean_mask(y_, mask)), K.floatx())


def loss(y_true, y_pred):
    '''
    This method calculates loss after masking predictions where true labels where padding (class 0)
    :param y_true: True value of y. One hot vector
    :param y_pred: Predicted value
    :return:
    '''
    # Calculate the loss for each item in the batch.
    loss_fn = CategoricalCrossentropy(reduction='none')
    loss = loss_fn(y_true, y_pred)

    y = tf.argmax(y_true, axis=-1)

    # Mask off the losses on padding.
    mask = tf.cast(y != 0, loss.dtype)
    loss *= mask

    # Return the total.
    return tf.reduce_sum(loss) / tf.reduce_sum(mask)


def train(model, data, acc_metric, batch_size, loss_metric, dir_name=None, patience=10, max_epochs=500):
    x_train, x_val, y_train, y_val = data

    # Check if the directory exists, create it if it doesn't
    if not os.path.exists(dir_name):
        os.makedirs(dir_name)

    model.compile(optimizer="rmsprop", loss=loss_metric, metrics=[acc_metric])

    early_stopping = tf.keras.callbacks.EarlyStopping(monitor=f'val_{acc_metric.__name__}', patience=patience)
    model_checkpoints = keras.callbacks.ModelCheckpoint(f"{dir_name}/protein.h5", save_best_only=True)
    history = model.fit(x_train, y_train, batch_size=batch_size, epochs=max_epochs, validation_data=(x_val, y_val), verbose=1,
                        callbacks=[model_checkpoints, early_stopping])
    model.save(f'.{dir_name}/')

    model_history = pd.DataFrame(history.history)
    model_history['epoch'] = history.epoch

    return model_history


def add_plot_to_figure(ax, name, model_history, metric_name):
    ax.plot(np.arange(0, model_history.shape[0]), model_history[metric_name],
            label=f"{name} train")
    ax.plot(np.arange(0, model_history.shape[0]), model_history[f"val_{metric_name}"],
            label=f"{name} val")
    ax.legend()
    return ax


def run_pipelines():
    input_csv_file = './data/2018-06-06-ss.cleaned.csv'
    MAX_SEQUENCE_LENGTH = 128
    BATCH_SIZE = 1024
    MAX_EPOCHS = 200
    VAL_SIZE = 0.4
    RANDOM_STATE = 0

    input_sequences, q3_targets = get_data(file_path=input_csv_file, max_sequence_length=MAX_SEQUENCE_LENGTH,
                                           input_column='seq', target_column='sst3')

    _, q8_targets = get_data(file_path=input_csv_file, max_sequence_length=MAX_SEQUENCE_LENGTH,
                             input_column='seq', target_column='sst8')

    input_data, input_tokenizer = preprocess(data=input_sequences, max_sequence_length=MAX_SEQUENCE_LENGTH, train=True)
    q3_targets, q3_tokenizer = preprocess(data=q3_targets, max_sequence_length=MAX_SEQUENCE_LENGTH, train=False)
    q8_targets, q8_tokenizer = preprocess(data=q8_targets, max_sequence_length=MAX_SEQUENCE_LENGTH, train=False)

    input_dim = len(input_tokenizer.word_index) + 1
    output_dim_q3 = len(q3_tokenizer.word_index) + 1
    output_dim_q8 = len(q8_tokenizer.word_index) + 1

    x_train_q3, x_val_q3, y_train_q3, y_val_q3 = train_test_split(input_data, q3_targets, test_size=VAL_SIZE,
                                                                  random_state=RANDOM_STATE)

    x_train_q8, x_val_q8, y_train_q8, y_val_q8 = train_test_split(input_data, q8_targets, test_size=VAL_SIZE,
                                                                  random_state=RANDOM_STATE)

    save_tokenizer(file_path="tokenizer/ngram_tokenizer.json", tokenizer=input_tokenizer)
    save_tokenizer(file_path="tokenizer/q3_tokenizer.json", tokenizer=q3_tokenizer)
    save_tokenizer(file_path="tokenizer/q8_tokenizer.json", tokenizer=q8_tokenizer)

    data_q3 = x_train_q3, x_val_q3, y_train_q3, y_val_q3
    data_q8 = x_train_q8, x_val_q8, y_train_q8, y_val_q8

    lstm_config = {
        'prediction_types': ['Q3', 'Q8'],
        'lstm_layer_types': [1, 2],
        'attention_types': [True, False]
    }

    models_dir = "models/"
    q3_models_results = []
    q8_models_results = []
    for prediction_type in lstm_config['prediction_types']:
        for lstm_layer_type in lstm_config['lstm_layer_types']:
            for attention_type in lstm_config['attention_types']:
                model_name = f"{prediction_type}_LSTM_{lstm_layer_type}L"
                if attention_type:
                    model_name += "_A"
                model = create_lstm_model(num_words=input_dim,
                                          num_tags=(output_dim_q3 if prediction_type == 'Q3' else output_dim_q8),
                                          lstm_layers=lstm_layer_type,
                                          attention=attention_type)
                print(f"Training {model_name}")
                model_history = train(model=model,
                                      data=(data_q3 if prediction_type == 'Q3' else data_q8),
                                      acc_metric=acc,
                                      batch_size=BATCH_SIZE,
                                      loss_metric=loss,
                                      dir_name=models_dir + model_name,
                                      max_epochs=MAX_EPOCHS)

                if prediction_type == 'Q3':
                    q3_models_results.append([model_name, model_history])
                else:
                    q8_models_results.append([model_name, model_history])

    transformer_config = {
        'prediction_types': ['Q3', 'Q8']
    }

    for prediction_type in transformer_config['prediction_types']:
        model_name = f"{prediction_type}_Transformer"
        model = create_transformer_model(num_words=input_dim,
                                  num_tags=(output_dim_q3 if prediction_type == 'Q3' else output_dim_q8))
        print(f"Training {model_name}")
        model_history = train(model=model,
                              data=(data_q3 if prediction_type == 'Q3' else data_q8),
                              acc_metric=acc,
                              batch_size=BATCH_SIZE,
                              loss_metric=loss,
                              dir_name=models_dir + model_name,
                              max_epochs=MAX_EPOCHS)

        if prediction_type == 'Q3':
            q3_models_results.append([model_name, model_history])
        else:
            q8_models_results.append([model_name, model_history])

    fig, ax = plt.subplots(1, figsize=(8, 6))
    for result in q3_models_results:
        ax = add_plot_to_figure(ax, result[0], result[1], metric_name="acc")
    ax.legend()
    plt.tight_layout()
    plt.savefig(models_dir + "Q3_accuracy_plot")
    plt.show()

    fig, ax = plt.subplots(1, figsize=(8, 6))
    for result in q8_models_results:
        ax = add_plot_to_figure(ax, result[0], result[1], metric_name="acc")
    ax.legend()
    plt.tight_layout()
    plt.savefig(models_dir + "Q8_accuracy_plot")
    plt.show()

    fig, ax = plt.subplots(1, figsize=(8, 6))
    for result in q3_models_results:
        ax = add_plot_to_figure(ax, result[0], result[1], metric_name="loss")
    ax.legend()
    plt.tight_layout()
    plt.savefig(models_dir + "Q3_loss_plot")
    plt.show()

    fig, ax = plt.subplots(1, figsize=(8, 6))
    for result in q8_models_results:
        ax = add_plot_to_figure(ax, result[0], result[1], metric_name="loss")
    ax.legend()
    plt.tight_layout()
    plt.savefig(models_dir + "Q8_loss_plot")
    plt.show()


if __name__ == "__main__":
    run_pipelines()

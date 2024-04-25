import json

import numpy as np
# from keras_preprocessing.sequence import pad_sequences
from keras.utils import pad_sequences
from keras.preprocessing.text import tokenizer_from_json
from tensorflow import keras
import tensorflow as tf


def f1(y_true, y_pred):
    return 1


def generate_n_grams(seqs, n=3):
    num_seqs = len(seqs)
    # Tokenizer need 1 dim input
    # In case of same length on all input sequences we need to initialize
    # array first in order to prevent np from creating a second dimension
    # We need seqs on format (x, ) and not (x, y)
    ngrams = np.empty(num_seqs, dtype=object)
    for s in range(num_seqs):
        ngrams[s] = ([seqs[s][i:i + n] for i in range(len(seqs[s]))])
    return ngrams


def predict(sequence):
    with open('tokenizer/ngram_tokenizer.json') as te:
        data = json.load(te)
        tokenizer_encoder = tokenizer_from_json(data)

    with open('tokenizer/q3_tokenizer.json') as td:
        data = json.load(td)
        tokenizer_decoder = tokenizer_from_json(data)

    model = keras.models.load_model('./models/Q3_LSTM_1L/protein.h5', custom_objects={'f1': f1}, compile=False)
    model.compile(metrics=["accuracy", f1])

    maxlen_seq = 128

    input_list = [sequence]
    input_grams = generate_n_grams(input_list)

    print(input_grams)
    input_data = tokenizer_encoder.texts_to_sequences(input_grams)
    input_data = pad_sequences(input_data, maxlen=maxlen_seq, padding='post')
    result = model.predict(input_data)
    pred = tf.argmax(result, axis=-1)
    print(pred)

    pred = tokenizer_decoder.sequences_to_texts(pred.numpy())

    return pred[0].replace(" ", "")





if __name__ == '__main__':
    print(predict("CCC"))

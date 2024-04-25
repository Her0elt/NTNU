import numpy as np
import tensorflow as tf
from keras import Input
from keras import backend as K
from keras.layers import LSTM, Embedding, Dense, TimeDistributed, Bidirectional, Dropout, MultiHeadAttention, \
    LayerNormalization, Concatenate, Activation, Lambda, \
    RepeatVector, Permute
from keras.models import Model


def create_lstm_model(num_words, num_tags, hidden_size=64, lstm_layers=1, dropout_rate=.1, attention=True):
    input_tensor = Input(shape=(None,))
    x = Embedding(input_dim=num_words, output_dim=128)(input_tensor)

    # Add specified number of LSTM layers
    for i in range(lstm_layers):
        if i == 0:
            x = Bidirectional(LSTM(units=hidden_size, return_sequences=True))(x)
        else:
            x = Bidirectional(LSTM(units=hidden_size, return_sequences=True))(x)
        x = Dropout(dropout_rate)(x)

    if attention:
        # Attention mechanism
        attention = Dense(1, activation='tanh')(x)
        attention = Activation('softmax')(attention)
        attention = Lambda(lambda x: x[0] * x[1])([x, attention])
        attention = Lambda(lambda x: K.sum(x, axis=1))(attention)
        attention = RepeatVector(2 * hidden_size)(attention)
        attention = Permute([2, 1])(attention)

        # Concatenate attention vector with LSTM output
        x = Concatenate(axis=-1)([x, attention])

    y = TimeDistributed(Dense(num_tags, activation="softmax"))(x)
    model = Model(input_tensor, y)
    return model


def positional_encoding(seq_length, d_model):
    position = K.arange(0, seq_length, dtype=K.floatx())
    position = K.expand_dims(position, 1)
    div_term = K.exp(K.arange(0, d_model, 2, dtype=K.floatx()) * -(np.log(10000.0) / d_model))
    div_term = K.expand_dims(div_term, 0)
    angles = K.dot(position, div_term)
    pos_encoding = K.concatenate([K.sin(angles), K.cos(angles)], axis=-1)
    pos_encoding = K.expand_dims(pos_encoding, 0)
    return pos_encoding


def create_transformer_model(num_words, num_tags, num_layers=2, d_model=64, num_heads=8, dff=512, dropout_rate=0.1):
    inputs = Input(shape=(None,), name='inputs')

    # Embedding layer
    embedding_layer = tf.keras.layers.Embedding(num_words, d_model)
    x = embedding_layer(inputs)

    # Positional encoding layer
    seq_length = tf.shape(inputs)[1]
    pos_encoding = positional_encoding(seq_length, d_model)
    x = x + pos_encoding
    x = Dropout(rate=dropout_rate)(x)

    # Transformer layers
    for i in range(num_layers):
        # Multi-head attention layer
        attention_output = MultiHeadAttention(num_heads=num_heads, key_dim=d_model)(x, x, attention_mask=None)
        attention_output = Dropout(rate=dropout_rate)(attention_output)
        attention_output = LayerNormalization(epsilon=1e-6)(x + attention_output)

        # Position-wise feedforward layer
        feedforward_output = Dense(units=dff, activation='relu')(attention_output)
        feedforward_output = Dense(units=d_model)(feedforward_output)
        feedforward_output = Dropout(rate=dropout_rate)(feedforward_output)
        x = LayerNormalization(epsilon=1e-6)(attention_output + feedforward_output)

    # Output layer
    outputs = TimeDistributed(Dense(units=num_tags, activation='softmax'), name='outputs')(x)

    # Create model
    model = Model(inputs=[inputs], outputs=[outputs])
    return model

import io
import json
import os

import numpy as np
import pandas as pd
# from keras.utils import to_categorical
from keras.utils.np_utils import to_categorical
# from keras.utils import pad_sequences
from keras_preprocessing.sequence import pad_sequences
from keras_preprocessing.text import Tokenizer


def sequence_to_ngrams(seqs, n=3):
    """This function take a list of string as input and return a list of ngrams for each string"""
    return np.array([[seq[i:i + n] for i in range(len(seq))] for seq in seqs], dtype=object)


def get_data(file_path, max_sequence_length, input_column, target_column):
    """This method reads a csv file and return inputs and targets based on desired columns"""
    df = pd.read_csv(file_path)
    df.len.hist(bins=100)

    inputs, targets = df[[input_column, target_column]][
        (df.len <= max_sequence_length) & (~df.has_nonstd_aa)].values.T

    return inputs, targets


def tokenize(data, char_level=False):
    tokenizer = Tokenizer(char_level=char_level)
    tokenizer.fit_on_texts(data)
    data = tokenizer.texts_to_sequences(data)

    return data, tokenizer


def preprocess(data, max_sequence_length, train=True):
    """
    This method tokenizes the data and pads the data if it is shorter than maximum sequence length.
    If the data is target data it also transforms it into a one-hot vector using to_categorical
    """
    if train is True:
        data = sequence_to_ngrams(data)
        data, tokenizer = tokenize(data, char_level=False)
        data = pad_sequences(data, maxlen=max_sequence_length, padding='post')
        return data, tokenizer
    else:
        data, tokenizer = tokenize(data, char_level=True)
        data = pad_sequences(data, maxlen=max_sequence_length, padding='post')
        data = to_categorical(data)
        return data, tokenizer

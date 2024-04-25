# This method saves a tokenizer for later use
import io
import json
import os


def save_tokenizer(file_path, tokenizer, encoding='utf-8'):
    # Extract directory path from file_path
    dir_path = os.path.dirname(file_path)

    # Check if the directory exists, create it if it doesn't
    if not os.path.exists(dir_path):
        os.makedirs(dir_path)

    """This function saves a tokenizer for later use"""
    tokenizer_json = tokenizer.to_json()
    with io.open(file_path, 'w', encoding=encoding) as f:
        f.write(json.dumps(tokenizer_json, ensure_ascii=False))

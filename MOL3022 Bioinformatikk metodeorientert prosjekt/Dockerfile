FROM python:3.9

#
WORKDIR /code

#
COPY ./requirements.txt /code/requirements.txt
COPY ./predict.py /code/predict.py
COPY ./models/ /code/models
COPY ./tokenizer/ /code/tokenizer

#
RUN pip install --no-cache-dir --upgrade -r /code/requirements.txt

#
COPY ./ /code/app

#
CMD ["uvicorn", "app.server:app", "--host", "0.0.0.0", "--port", "8080"]

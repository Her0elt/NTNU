from fastapi import FastAPI
from pydantic import BaseModel
from fastapi.middleware.cors import CORSMiddleware
from predict import predict

#import keras


app = FastAPI()

origins = [
    "http://localhost:3000",
    "localhost:3000"
]


app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"]
)



@app.get("/wake-up")
async def wake_up():
    return { "status": "awake" }


class Sequence(BaseModel):
    sequence: str


@app.post("/predict")
async def use_predict(data: Sequence):
    ans = predict(data.sequence)
    return { "ans": ans, "sequence": data.sequence }


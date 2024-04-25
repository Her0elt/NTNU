import type { PredictionRequest, PredictionResponse } from "~/types";
import { HttpRequest } from "./HttpRequest.server";

export const PREDICT_URL = 'predict';
export const WAKE_UP_URL = 'wake-up';


export const PREDICTION_API = (request: Request) => ({
    predict:(data: PredictionRequest) => new HttpRequest(PREDICT_URL, request).post<PredictionResponse>(data),
    wakeUp : () => new HttpRequest(WAKE_UP_URL, request).get<{ status: string }>()

})

export type RequestResponse = {
  detail: string;
};

export type ActionResponse = {
  ok: boolean;
  violations?: Partial<Record<string, string | number | null | undefined>>;
} & Partial<RequestResponse>;


export type PredictionResponse = {
    ans: string;
    sequence: string;
}
export type PredictionRequest = {
    sequence: string;
}

import type { PredictionResponse } from "~/types";
import Paper from "./Paper";


export type PredictionRendererProps = {
    data: PredictionResponse;
}

export default function PredictionRenderer({ data } : PredictionRendererProps) {
    return (
        <Paper>
            <h1 className="text-2xl">Original protien sequence</h1>
            <p className="text-xl break-all">{data.sequence}</p>
            <h1 className="text-2xl">Predicted secondary structure</h1>
            <p className="text-xl break-all">{data.ans}</p>
            <p></p>
        </Paper>
    )
}

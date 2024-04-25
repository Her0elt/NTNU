import type { ActionArgs } from '@remix-run/node';
import { useActionData } from '@remix-run/react';
import { PREDICTION_API } from '~/api/predict.server';
import type{ PredictionRequest, PredictionResponse } from '~/types';
import Paper from '~/components/Paper';
import Button from '~/components/Button';
import PredictionRenderer from '~/components/PredictionRenderer';
import { Suspense } from "react";
import type { LoaderArgs } from "@remix-run/node"; // or cloudflare/deno
import { defer } from "@remix-run/node"; // or cloudflare/deno
import { Await, useLoaderData } from "@remix-run/react";

 export const  loader = ({ params, request }: LoaderArgs) => {
     const wakeup = PREDICTION_API(request).wakeUp();
     return defer({ wakeup });
 }


export const action = async ({ request, params }: ActionArgs) => {
    const formData = await request.formData()
    if (request.method == "POST") {
        const data: PredictionRequest = {
          "sequence": formData.get("sequence") as string

        }

        return PREDICTION_API(request).predict(data);
    }

};
export default function Predict() {
    const actionData = useActionData<typeof action>();
    const data = useLoaderData<typeof loader>();

  return (
    <div className='grid justify-start m-10 grid-cols-1 bg-primary gap-4 bg-primary xl:grid-cols-2'>
    <Suspense
        fallback={<Paper><h1> Wating for the prediction api to wake up</h1> </Paper>}
      >
      <Await
          resolve={data.wakeup}
          errorElement={
            <p>Error loading api!</p>
          }
        >
        <Paper>
        <div className='grid gap-12'>
        <h1 className='text-2xl bg-secondary'> Input a protein sequence</h1>
        <form method='post'>
        <label className='mb-2 italic bg-secondary' htmlFor='company'>
              Sequence
            </label>
            <textarea
            cols={40}
            rows={10}
              className='block p-2.5 w-full text-sm text-gray-900 bg-gray-50 rounded-lg border border-gray-300 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500 mb-10'
              placeholder="Protein sequence"
              defaultValue={""}
              id='sequence'
              name='sequence'
              required
            />


        <Button type="submit">Predict</Button>
        </form>
        </div>
        </Paper>
        { actionData && <PredictionRenderer data={actionData as PredictionResponse}/>}
        </Await>
        </Suspense>
    </div>

  );
}


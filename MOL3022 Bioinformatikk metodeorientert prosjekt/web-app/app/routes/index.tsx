import type { ActionArgs } from '@remix-run/node';
import { Link, useActionData } from '@remix-run/react';
import Paper from '~/components/Paper';


export default function Index() {
  return (
    <div className='grid justify-center	p-8 m-44'>
        <Paper >
             <h1 className='text-white text-6xl'>Welcome to the protein predicter</h1>
             <p className='text-2xl'>
                This tool allows you to predict the secondary structure of a protein sequence
             </p>
             <p className='text-2xl'>
                The tool was created for the sucject MOL3022 at NTNU
             </p>
             <nav>
                <Link className='mt-0 mb-2 text-xl font-medium leading-tight' to="/predict">Go to prediction page</Link>{" "}
            </nav>
        </Paper>
    </div>

  );
}

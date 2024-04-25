import type { PropsWithChildren } from 'react';

export type ButtonProps = {
  onClick?: () => void;
  type?: 'button' | 'submit' | 'reset';
};

export default function Button({ onClick, children, type }: PropsWithChildren<ButtonProps>) {
  return (
    <button
      className='rounded border-b-4 border-sky-800 bg-sky-900 py-2 px-4 font-bold text-white hover:border-sky-500 hover:bg-sky-400'
      onClick={onClick}
      type={type}>
      {children}
    </button>
  );
};

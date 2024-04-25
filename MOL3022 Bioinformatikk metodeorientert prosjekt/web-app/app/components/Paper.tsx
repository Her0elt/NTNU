import type { PropsWithChildren } from 'react';

export type PaperProps = {
  onClick?: () => void;
};

export default function Paper({ children, onClick }: PropsWithChildren<PaperProps>) {
  return (
    <div className={`grid grid-cols-1 place-items-start gap-6 rounded-lg bg-secondary p-8  shadow-2xl xl:p-16 justify-center`} onClick={onClick}>
      {children}
    </div>
  );
};

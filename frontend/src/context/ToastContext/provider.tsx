import { useState } from 'react';
import { ToastContext } from './context';
import { ToastInput, ToastContextType } from './type';

type ToastContextProviderProps = {
  children: React.ReactNode;
};

export const ToastContextProvider: React.FC<ToastContextProviderProps> = ({
  children
}) => {
  const [toastState, setToastState] = useState<ToastInput>({
    type: 'success',
    message: ''
  });

  const value: ToastContextType = {
    toast: toastState,
    setToast: (newToastMessage: ToastInput) => {
      setToastState(newToastMessage);
    }
  };

  return (
    <ToastContext.Provider value={value}>{children}</ToastContext.Provider>
  );
};

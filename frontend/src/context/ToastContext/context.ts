import { createContext } from 'react';
import { ToastContextType } from './type';

export const ToastContext = createContext<ToastContextType>({
  toast: { type: 'success', message: '' },
  setToast: () => {
    throw new Error('setToastMessage function must be overridden');
  }
});

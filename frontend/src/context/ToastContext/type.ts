export type ToastContextType = {
  toast: ToastInput;
  setToast: (toast: ToastInput) => void;
};

export type ToastInput = {
  type?: 'success' | 'error';
  message?: string;
};

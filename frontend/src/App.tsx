import React from 'react';
import Routes from './routes';
import { ToastContextProvider } from '@src/context';

function App() {
  return (
    <ToastContextProvider>
      <Routes />
    </ToastContextProvider>
  );
}

export default App;

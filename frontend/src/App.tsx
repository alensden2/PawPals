import React from 'react';
import Routes from './routes';
import { ToastContextProvider, HeaderContextProvider } from '@src/context';

function App() {
  return (
    <ToastContextProvider>
      <HeaderContextProvider>
        <Routes />
      </HeaderContextProvider>
    </ToastContextProvider>
  );
}

export default App;

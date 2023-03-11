import React, { useContext } from 'react';

import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { Landing, SignIn, SignUp } from '@src/pages';
import { Toast } from '@src/components';
import { ToastContext } from '@src/context';

const RoutesComp = () => {
  const { toast } = useContext(ToastContext);

  return (
    <BrowserRouter>
      <Toast toast={toast} />
      <Routes>
        <Route>
          <Route path="/" element={<Landing />} />
          <Route path="signin" element={<SignIn />} />
          <Route path="signup" element={<SignUp />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
};

export default RoutesComp;

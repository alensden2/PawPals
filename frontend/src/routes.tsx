import React from 'react';

import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { Landing, SignIn, SignUp, Root } from '@src/pages';

const RoutesComp = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Root />}>
          <Route path="/" element={<Landing />} />
          <Route path="signin" element={<SignIn />} />
          <Route path="signup" element={<SignUp />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
};

export default RoutesComp;

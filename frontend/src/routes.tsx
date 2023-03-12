import React from 'react';

import { BrowserRouter, Route, Routes } from 'react-router-dom';
import {
  Landing,
  SignIn,
  SignUp,
  Root,
  PetOwner,
  PetOwnerHome,
  PetOwnerRegistration,
  Vet,
  VetHome,
  VetRegistration,
  Admin,
  AdminHome
} from '@src/pages';
import { Landing, SignIn, SignUp, PetOwnerRegistration } from '@src/pages';

const RoutesComp = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Root />}>
          <Route path="/" element={<Landing />} />
          <Route path="signin" element={<SignIn />} />
          <Route path="signup" element={<SignUp />} />
          <Route path="pet-owner" element={<PetOwner />}>
            <Route path="home" element={<PetOwnerHome />} />
            <Route path="registration" element={<PetOwnerRegistration />} />
          </Route>
          <Route path="vet" element={<Vet />}>
            <Route path="home" element={<VetHome />} />
            <Route path="registration" element={<VetRegistration />} />
          </Route>
          <Route path="admin" element={<Admin />}>
            <Route path="home" element={<AdminHome />} />
          </Route>
          <Route path="pet-owner-registration" element={<PetOwnerRegistration />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
};

export default RoutesComp;

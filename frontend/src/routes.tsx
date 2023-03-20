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
  AdminHome,
  PetHealthAndDiseaseInfo,
  PetMedicalHistory,
  PetOwnerAllVets,
  AboutUs,
  PetOwnerManagePets
} from '@src/pages';

const RoutesComp = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Root />}>
          <Route path="/" element={<Landing />} />
          <Route path="signin" element={<SignIn />} />
          <Route path="signup" element={<SignUp />} />
          <Route path="about-us" element={<AboutUs />} />
          <Route path="pet-owner" element={<PetOwner />}>
            <Route path="home" element={<PetOwnerHome />} />
            <Route path="registration" element={<PetOwnerRegistration />} />
            <Route path="manage-pets" element={<PetOwnerManagePets />} />
            <Route path="medical-history" element={<PetMedicalHistory />} />
            <Route path="all-vets" element={<PetOwnerAllVets />} />
            <Route
              path="all-vets/book-appointment/:vet_id"
              element={<PetOwnerRegistration />}
            />
            <Route
              path="pet-health-and-diseases"
              element={<PetHealthAndDiseaseInfo />}
            />
          </Route>
          <Route path="vet" element={<Vet />}>
            <Route path="home" element={<VetHome />} />
            <Route path="registration" element={<VetRegistration />} />
          </Route>
          <Route path="admin" element={<Admin />}>
            <Route path="home" element={<AdminHome />} />
          </Route>
        </Route>
      </Routes>
    </BrowserRouter>
  );
};

export default RoutesComp;

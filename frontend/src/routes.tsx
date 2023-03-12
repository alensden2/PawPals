import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { Landing, SignIn, SignUp, PetOwnerRegistration } from '@src/pages';

const RoutesComp = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route>
          <Route path="/" element={<Landing />} />
          <Route path="signin" element={<SignIn />} />
          <Route path="signup" element={<SignUp />} />
          <Route path="pet-owner-registration" element={<PetOwnerRegistration />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
};

export default RoutesComp;

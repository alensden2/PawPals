import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { Landing, SignIn, SignUp } from './pages';

const RoutesComp = () => {
  return (
    <BrowserRouter>
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

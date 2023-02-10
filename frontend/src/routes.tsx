import { BrowserRouter, Route, Routes } from 'react-router-dom';
import App from './App';
import { Landing, AboutMe } from './pages';

const RoutesComp = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<App />}>
          <Route path="landing" element={<Landing />} />
          <Route path="aboutme" element={<AboutMe />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
};

export default RoutesComp;

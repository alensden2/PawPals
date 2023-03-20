import React from 'react';
import { Outlet } from 'react-router-dom';

const AboutUs: React.FC = () => {
  return (
    <div>
      <div>{'About Us'}</div>
      <Outlet />
    </div>
  );
};

export default AboutUs;

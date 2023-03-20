import React from 'react';
import { Outlet } from 'react-router-dom';

const ContactUs: React.FC = () => {
  return (
    <div>
      <div>{'Contact Us'}</div>
      <Outlet />
    </div>
  );
};

export default ContactUs;

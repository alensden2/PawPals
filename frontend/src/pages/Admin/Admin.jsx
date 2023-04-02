import React from 'react';
import { Outlet } from 'react-router-dom';

const Admin = () => {
  return (
    <div>
      <div>{'Admin Home Page'}</div>
      <Outlet />
    </div>
  );
};

export default Admin;

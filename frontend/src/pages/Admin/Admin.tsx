import React from 'react';
import { Outlet } from 'react-router-dom';

const Admin: React.FC = () => {
  return (
    <div>
      <div>{'Admin Home Page'}</div>
      <Outlet />
    </div>
  );
};

export default Admin;

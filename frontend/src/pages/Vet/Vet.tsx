import React from 'react';
import { Outlet } from 'react-router-dom';

const Vet: React.FC = () => {
  return (
    <div>
      <Outlet />
    </div>
  );
};

export default Vet;

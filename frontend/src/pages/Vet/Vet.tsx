import React from 'react';
import { Outlet } from 'react-router-dom';

const Vet: React.FC = () => {
  return (
    <div>
      {'Vet'}
      <Outlet />
    </div>
  );
};

export default Vet;

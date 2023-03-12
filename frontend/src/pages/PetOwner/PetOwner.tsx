import React from 'react';
import { Outlet } from 'react-router-dom';

const PetOwner: React.FC = () => {
  return (
    <div>
      <div>{'PetOwner'}</div>
      <Outlet />
    </div>
  );
};

export default PetOwner;

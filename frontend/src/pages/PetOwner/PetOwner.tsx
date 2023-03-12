import React, { useEffect } from 'react';
import { Outlet } from 'react-router-dom';
import { useNavigate } from '@src/hooks';

const PetOwner: React.FC = () => {
  const navigate = useNavigate();

  useEffect(() => {
    navigate('registration');
  }, []);

  return (
    <div>
      <Outlet />
    </div>
  );
};

export default PetOwner;

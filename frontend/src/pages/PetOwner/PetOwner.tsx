import React, { useEffect } from 'react';
import { Outlet } from 'react-router-dom';
import { useNavigate } from '@src/hooks';

const PetOwner: React.FC = () => {
  const navigate = useNavigate();

  useEffect(() => {
    navigate('home');
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return (
    <>
      <Outlet />
    </>
  );
};

export default PetOwner;

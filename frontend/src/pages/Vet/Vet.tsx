import React, { useEffect } from 'react';
import { Outlet } from 'react-router-dom';
import { useNavigate } from '@src/hooks';

const Vet: React.FC = () => {
  const navigate = useNavigate();

  useEffect(() => {
    navigate('home');
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return (
    <div>
      <Outlet />
    </div>
  );
};

export default Vet;

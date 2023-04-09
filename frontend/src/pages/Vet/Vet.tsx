import React, { useEffect } from 'react';
import { Outlet } from 'react-router-dom';
import { useNavigate } from '@src/hooks';
import { localStorageUtil } from '@src/utils';

const Vet: React.FC = () => {
  const navigate = useNavigate();

  useEffect(() => {
    const user = localStorageUtil.getItem('user');

    const roles = ['VET', 'ROLE_VET'];
    if (roles.includes(user?.role)) {
      navigate('home');
    } else {
      navigate('/', { replace: true });
    }

    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return (
    <div>
      <Outlet />
    </div>
  );
};

export default Vet;

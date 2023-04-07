import React, { useEffect } from 'react';
import { Outlet } from 'react-router-dom';
import { useNavigate } from '@src/hooks';
import useStyles from './Admin.styles';
import { localStorageUtil } from '@src/utils';

const Admin = () => {
  const navigate = useNavigate();
  const classes = useStyles();

  useEffect(() => {
    const user = localStorageUtil.getItem('user');

    const roles = ['ADMIN', 'ROLE_ADMIN'];
    if (roles.includes(user?.role)) {
      navigate('home');
    } else {
      navigate('/', { replace: true });
    }

    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return (
    <div className={classes.root}>
      <Outlet />
    </div>
  );
};

export default Admin;

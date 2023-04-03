import React, { useEffect } from 'react';
import { Outlet } from 'react-router-dom';
import { useNavigate } from '@src/hooks';
import useStyles from './Admin.styles';

const Admin = () => {
  const navigate = useNavigate();
  const classes = useStyles();

  useEffect(() => {
    navigate('home');
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return (
    <div className={classes.root}>
      <Outlet />
    </div>
  );
};

export default Admin;

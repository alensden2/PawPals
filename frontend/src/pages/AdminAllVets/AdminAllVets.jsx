/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck

import React, { useContext, useEffect } from 'react';
import useStyles from './AdminAllVets.styles';
import { HeaderContext } from '@src/context';

const AdminAllVets = () => {
  const classes = useStyles();
  const { setHeader } = useContext(HeaderContext);

  useEffect(() => {
    setHeader({
      shouldShowHeader: true,
      title: 'All Vets',
      shouldShowLogoutButton: true,
      shouldShowBackButton: true,
      backRoute: '/admin/home'
    });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return <div>{'Admin All Vets'}</div>;
};

export default AdminAllVets;

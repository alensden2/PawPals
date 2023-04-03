/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck

import React, { useContext, useEffect } from 'react';
import useStyles from './AdminAllPetOwners.styles';
import { HeaderContext } from '@src/context';

const AdminAllPetOwners = () => {
  const classes = useStyles();

  const { setHeader } = useContext(HeaderContext);

  useEffect(() => {
    setHeader({
      shouldShowHeader: true,
      title: 'All Pet Owners',
      shouldShowLogoutButton: true,
      shouldShowBackButton: true,
      backRoute: '/admin/home'
    });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return <div>{'Admin All Pet Owners'}</div>;
};

export default AdminAllPetOwners;

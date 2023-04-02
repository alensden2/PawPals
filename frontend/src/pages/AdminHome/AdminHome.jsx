import React, { useContext, useEffect } from 'react';
import { HeaderContext } from '@src/context';

const AdminHome = () => {
  const { setHeader } = useContext(HeaderContext);

  useEffect(() => {
    setHeader({
      shouldShowHeader: true,
      title: 'Admin Home',
      shouldShowLogoutButton: true,
      shouldShowBackButton: true
    });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return <div>{'Admin Home'}</div>;
};

export default AdminHome;

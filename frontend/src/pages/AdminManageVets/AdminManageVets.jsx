/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck

import React, { useContext, useEffect, useState } from 'react';
import { Loader, EmptyState } from '@src/components';
import { HeaderContext } from '@src/context';

const AdminManageVets = () => {
  const { setHeader } = useContext(HeaderContext);

  const [isLoading, setIsLoading] = useState(false);
  const [pendingVets, setPendingVets] = useState([]);

  useEffect(() => {
    setHeader({
      shouldShowHeader: true,
      title: 'Manage Vets',
      shouldShowLogoutButton: true,
      shouldShowBackButton: true,
      backRoute: '/admin/home'
    });

    setIsLoading(true);
    // call api
    setPendingVets([]);
    setIsLoading(false);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const render = () => {
    if (isLoading) {
      return <Loader />;
    }

    if (pendingVets?.length === 0) {
      return <EmptyState text={'No Pending Request!'} />;
    }

    return <div>Table here</div>;
  };

  return <div>{render()}</div>;
};

export default AdminManageVets;

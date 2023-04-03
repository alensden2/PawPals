/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck

import React, { useContext, useEffect, useState } from 'react';
import { Loader, EmptyState } from '@src/components';
import { HeaderContext, ToastContext } from '@src/context';
import { getAllPendingVets } from '@src/api';
import ManageVetsTable from './ManageVetsTable';
import useStyles from './AdminManageVets.styles';

const AdminManageVets = () => {
  const classes = useStyles();
  const { setHeader } = useContext(HeaderContext);
  const { setToast } = useContext(ToastContext);

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

    async function fetchData() {
      try {
        setIsLoading(true);
        const response = await getAllPendingVets();
        setPendingVets(response);
        setIsLoading(false);
      } catch (e) {
        console.error(e);
      }
    }
    fetchData();

    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const handleApproveVet = (vet) => {
    const userName = vet.userName;

    setPendingVets((prevState) => {
      return prevState.map((item) =>
        item.userName === userName
          ? {
              ...item,
              profileStatus: 'APPROVED'
            }
          : item
      );
    });

    setToast({
      type: 'success',
      message: 'Approval Successful. Vet can now sign in.'
    });
  };

  const handleRejectVet = (vet) => {
    const userName = vet.userName;
    setPendingVets((prevState) => {
      return prevState.map((item) =>
        item.userName === userName
          ? {
              ...item,
              profileStatus: 'REJECTED'
            }
          : item
      );
    });

    setToast({
      type: 'error',
      message: 'Vet Request Rejected'
    });
  };

  const render = () => {
    if (isLoading) {
      return <Loader />;
    }

    if (pendingVets?.length === 0) {
      return <EmptyState text={'No Pending Request!'} />;
    }

    return (
      <div>
        <ManageVetsTable
          pendingVets={pendingVets}
          handleApproveVet={handleApproveVet}
          handleRejectVet={handleRejectVet}
        />
      </div>
    );
  };

  return <div className={classes.root}>{render()}</div>;
};

export default AdminManageVets;

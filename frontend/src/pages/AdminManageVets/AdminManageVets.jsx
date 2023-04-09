// @ts-nocheck

import React, { useContext, useEffect, useState } from 'react';
import { Loader, EmptyState } from '@src/components';
import { HeaderContext, ToastContext } from '@src/context';
import { getAllPendingVets, updateProfileStatusVet } from '@src/api';
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

  const handleApproveVet = async (vet) => {
    const userName = vet.userName;

    const input = {
      profileStatus: 'APPROVED'
    };

    const isSuccess = await updateProfileStatusVet({
      input,
      vetUserId: userName
    });

    if (isSuccess) {
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
    } else {
      setToast({
        type: 'error',
        message: 'Something went wrong'
      });
    }
  };

  const handleRejectVet = async (vet) => {
    const userName = vet.userName;

    const input = {
      profileStatus: 'REJECTED'
    };

    const isSuccess = await updateProfileStatusVet({
      input,
      vetUserId: userName
    });

    if (isSuccess) {
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
    } else {
      setToast({
        type: 'error',
        message: 'Something went wrong'
      });
    }
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

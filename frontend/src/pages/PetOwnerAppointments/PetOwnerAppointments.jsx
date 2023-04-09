// @ts-nocheck

import React, { useEffect, useContext, useState } from 'react';
import useStyles from './PetOwnerAppointments.styles';
import { HeaderContext } from '@src/context';
import { getAllPetOwnerAppointments } from '@src/api';
import { Loader, EmptyState } from '@src/components';
import AppointmentsCardList from './AppointmentsCardList';

const PetOwnerAppointments = () => {
  const { setHeader } = useContext(HeaderContext);
  const [isLoading, setIsLoading] = useState(false);

  const [appointments, setAppointments] = useState([]);

  const classes = useStyles();

  useEffect(() => {
    setHeader({
      shouldShowHeader: true,
      title: 'My Appointments',
      backRoute: '/pet-owner/home',
      shouldShowLogoutButton: true,
      shouldShowBackButton: true
    });

    async function fetchData() {
      try {
        setIsLoading(true);
        const result = await getAllPetOwnerAppointments();
        setAppointments(result);
        setIsLoading(false);
      } catch (e) {
        console.error(e);
      }
    }
    fetchData();

    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const render = () => {
    if (isLoading) {
      return <Loader />;
    }

    if (appointments?.length === 0) {
      return <EmptyState text="No booked appointments!" />;
    }

    return <AppointmentsCardList appointments={appointments} />;
  };

  return <div className={classes.root}>{render()}</div>;
};

export default PetOwnerAppointments;

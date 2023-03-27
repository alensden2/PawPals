/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck
import React, { useContext, useEffect, useState } from 'react';
import { HeaderContext } from '@src/context';

import useStyles from './VetHome.styles';
import { vetAppointments } from '@src/data';
import HorizontalList from './HorizontalList';
import AppointmentDetailsModal from './AppointmentDetailsModal';

const VetHome = () => {
  const { setHeader } = useContext(HeaderContext);
  const classes = useStyles();
  const [allAppointments, setAllAppointments] = useState(
    vetAppointments.allAppointments
  );
  const [appointmentDetailsModal, setAppointmentDetailsModal] = useState({
    isOpen: false,
    appointment: {}
  });

  const openAppointmentDetailsModal = ({ appointment = null }) => {
    let newState = {
      isOpen: true
    };

    if (appointment) {
      newState = {
        isOpen: true,
        appointment
      };
    }

    setAppointmentDetailsModal((prevValue) => ({
      ...prevValue,
      ...newState
    }));
  };

  const closeAppointmentDetailsModal = () => {
    console.log('-------------1-------------');
    setAppointmentDetailsModal((prevValue) => ({
      ...prevValue,
      isOpen: false,
      appointment: null
    }));
  };

  useEffect(() => {
    setHeader({
      shouldShowHeader: true,
      title: 'Home',
      shouldShowLogoutButton: true,
      shouldShowBackButton: true
    });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const getFilteredAppointmentsByStatus = (status) => {
    return allAppointments.filter((item) => item.appointment.status === status);
  };

  const renderHorizontalList = (status) => {
    return (
      <HorizontalList
        appointments={getFilteredAppointmentsByStatus(status)}
        status={status}
        openAppointmentDetailsModal={openAppointmentDetailsModal}
      />
    );
  };

  const renderAppointments = () => {
    return (
      <>
        {renderHorizontalList('PENDING')}
        {renderHorizontalList('CONFIRMED')}
        {renderHorizontalList('DIAGNOSED')}
      </>
    );
  };

  return (
    <div className={classes.root}>
      {renderAppointments()}
      {appointmentDetailsModal.isOpen ? (
        <AppointmentDetailsModal
          appointmentDetailsModal={appointmentDetailsModal}
          closeAppointmentDetailsModal={closeAppointmentDetailsModal}
        />
      ) : null}
    </div>
  );
};

export default VetHome;

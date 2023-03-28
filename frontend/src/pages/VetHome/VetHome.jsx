/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck
import React, { useContext, useEffect, useState } from 'react';
import { HeaderContext } from '@src/context';

import useStyles from './VetHome.styles';
import { vetAppointments } from '@src/data';
import HorizontalList from './HorizontalList';
import AppointmentDetailsModal from './AppointmentDetailsModal';
import DiagnoseModal from './DiagnoseModal';

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
  const initialDiagnoseModalState = {
    isOpen: false,
    data: {
      petId: null
    }
  };
  const [diagnoseModal, setDiagnoseModal] = useState(initialDiagnoseModalState);
  const [isLoading, setIsLoading] = useState(false);

  useEffect(() => {
    async function fetchData() {
      try {
        setIsLoading(true);
        const result = await getAllAppointmentsOfVet();

        setAllAppointments(result);
        setIsLoading(false);
      } catch (e) {
        console.error(e);
      }
    }

    fetchData();
  }, []);

  const onDiagnoseButtonClick = ({ petId, openModal }) => {
    setDiagnoseModal((prevState) => {
      return {
        ...prevState,
        isOpen: openModal,
        data: {
          ...prevState.data,
          petId
        }
      };
    });
  };

  const onDiagnoseModalCancelButtonClick = () => {
    setDiagnoseModal(initialDiagnoseModalState);
  };

  const onApproveAppointmentClick = ({ appointmentId }) => {
    setAllAppointments((prevState) => {
      return prevState.map((appointment) => {
        if (appointment.appointment.id === appointmentId) {
          return {
            ...appointment,
            appointment: {
              ...appointment.appointment,
              status: 'CONFIRMED'
            }
          };
        }
        return appointment;
      });
    });
  };

  const onDeclineAppointmentClick = ({ appointmentId }) => {
    setAllAppointments((prevState) => {
      return prevState.map((appointment) => {
        if (appointment.appointment.id === appointmentId) {
          return {
            ...appointment,
            appointment: {
              ...appointment.appointment,
              status: 'REJECTED'
            }
          };
        }
        return appointment;
      });
    });
  };

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
        onApproveAppointmentClick={onApproveAppointmentClick}
        onDeclineAppointmentClick={onDeclineAppointmentClick}
        onDiagnoseButtonClick={onDiagnoseButtonClick}
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

  const render = () => {
    if (isLoading) {
      return <Loader />;
    }

    return (
      <>
        {renderAppointments()}
        {appointmentDetailsModal.isOpen ? (
          <AppointmentDetailsModal
            appointmentDetailsModal={appointmentDetailsModal}
            closeAppointmentDetailsModal={closeAppointmentDetailsModal}
          />
        ) : null}
        {diagnoseModal.isOpen ? (
          <DiagnoseModal
            handleClose={onDiagnoseModalCancelButtonClick}
            isOpen={diagnoseModal.isOpen}
          />
        ) : null}
      </>
    );
  };

  return <div className={classes.root}>{render()}</div>;
};

export default VetHome;

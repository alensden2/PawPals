/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck
import React, { useContext, useEffect, useState } from 'react';
import { HeaderContext, ToastContext } from '@src/context';

import useStyles from './VetHome.styles';
import HorizontalList from './HorizontalList';
import AppointmentDetailsModal from './AppointmentDetailsModal';
import DiagnoseModal from './DiagnoseModal';
import { getAllAppointmentsOfVet, updateStatusOfAppointment } from '@src/api';
import { Loader } from '@src/components';

const VetHome = () => {
  const { setHeader } = useContext(HeaderContext);
  const classes = useStyles();
  const { setToast } = useContext(ToastContext);

  const [allAppointments, setAllAppointments] = useState([]);
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

  const onApproveAppointmentClick = async ({ appointmentId }) => {
    const isSuccess = await updateStatusOfAppointment({
      appointmentId,
      input: {
        status: 'CONFIRMED'
      }
    });

    if (isSuccess) {
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
    } else {
      setToast({ type: 'error', message: 'Something went wrong!' });
    }
  };

  const onDeclineAppointmentClick = async ({ appointmentId }) => {
    const isSuccess = await updateStatusOfAppointment({
      appointmentId,
      input: {
        status: 'REJECTED'
      }
    });

    if (isSuccess) {
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
    } else {
      setToast({ type: 'error', message: 'Something went wrong!' });
    }
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

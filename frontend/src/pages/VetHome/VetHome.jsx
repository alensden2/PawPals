// @ts-nocheck
import React, { useContext, useEffect, useState } from 'react';
import { HeaderContext, ToastContext } from '@src/context';

import useStyles from './VetHome.styles';
import HorizontalList from './HorizontalList';
import AppointmentDetailsModal from './AppointmentDetailsModal';
import DiagnoseModal from './DiagnoseModal';
import {
  getAllAppointmentsOfVet,
  updateStatusOfAppointment,
  createMedicalRecord
} from '@src/api';
import { Loader } from '@src/components';
import { localStorageUtil, getTodayDate } from '@src/utils';

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

  const onDiagnoseButtonClick = ({ petId, openModal, appointmentId }) => {
    setDiagnoseModal((prevState) => {
      return {
        ...prevState,
        isOpen: openModal,
        data: {
          ...prevState.data,
          petId,
          appointmentId
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

      setToast({
        type: 'success',
        message: 'The appointment was approved successfully.'
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
      setToast({
        type: 'success',
        message: 'The appointment was declined successfully.'
      });
    } else {
      setToast({ type: 'error', message: 'Something went wrong!' });
    }
  };

  const onDiagnoseModalSubmitClick = async ({
    ailmentName,
    prescription,
    vaccines
  }) => {
    const petId = diagnoseModal.data.petId;
    const appointmentId = diagnoseModal.data.appointmentId;
    const user = localStorageUtil.getItem('user');
    const vetUserId = user.userName;

    let isSuccess = false;

    isSuccess = await createMedicalRecord({
      input: {
        ailmentName,
        prescription,
        vaccines,
        animalId: petId,
        vetUserId,
        dateDiagnosed: getTodayDate()
      }
    });

    if (isSuccess) {
      isSuccess = await updateStatusOfAppointment({
        appointmentId,
        input: {
          status: 'COMPLETED'
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
                  status: 'COMPLETED'
                }
              };
            }
            return appointment;
          });
        });

        setToast({
          type: 'success',
          message: 'The pet has been diagnosed successfully.'
        });
      } else {
        setToast({ type: 'error', message: 'Something went wrong!' });
      }
    }

    setDiagnoseModal({
      isOpen: false,
      data: {
        petId: null,
        appointmentId: null
      }
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
      title: 'Vet Home',
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
        {renderHorizontalList('COMPLETED')}
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
            onDiagnoseModalSubmitClick={onDiagnoseModalSubmitClick}
          />
        ) : null}
      </>
    );
  };

  return <div className={classes.root}>{render()}</div>;
};

export default VetHome;

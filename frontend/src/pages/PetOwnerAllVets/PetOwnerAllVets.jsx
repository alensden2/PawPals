/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck

import React, { useContext, useEffect, useState } from 'react';
import Modal from '@material-ui/core/Modal';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import { Button } from '@material-ui/core';


import { HeaderContext, ToastContext } from '@src/context';
import VetCardList from './VetCardList';
import { Loader, EmptyState, Select } from '@src/components';
import useStyles from './PetOwnerAllVets.styles';
import { getAllVets, getAllPetsForUser, bookAppointment } from '@src/api';
import { getVetAvailabilityOnSpecificDay } from '@src/api/vet';
import { vetsData as vetsMockData } from '@src/data/vet';
import { localStorageUtil } from '@src/utils';

const PetOwnerAllVets = () => {
  const { setHeader } = useContext(HeaderContext);
  const { setToast } = useContext(ToastContext);
  const classes = useStyles();

  const [vetsState, setVetsState] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [modalState, setModalState] = useState({isOpen: false, onClose: onModalClose, vet:undefined});
  const [dateSelected, setDateSelected] = useState(null);
  const [timeSelected, setTimeSelected] = useState("");
  const [petSelected, setPetSelected] = useState({label:"", value:""});
  const [dateOptions, setDateOptions] = useState([]);
  const [petOptions, setPetOptions] = useState([]);
  
  useEffect(() => {
    setHeader({
      shouldShowHeader: true,
      title: 'All Veterinarian',
      backRoute: '/pet-owner/home',
      shouldShowLogoutButton: true,
      shouldShowBackButton: true
    });


    async function fetchData() {
      try {
        setIsLoading(true);
        const result = await getAllVets();
        setIsLoading(false);
        setVetsState(result);
      } catch (e) {
        console.error(e);
      }
    }
    fetchData();

    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const handleBookClick = (vetUserId) => {

    async function getAllAnimals(userName){
      const response = await getAllPetsForUser(userName);
      return response;
    }
    const user = localStorageUtil.getItem('user');

    if(user && user.userName){
      getAllAnimals(user.userName).then((allAnimals)=>{
        setPetOptions(allAnimals.map(animal=>({label:animal.name, value: animal.id})));
        setModalState((prevState)=>{
          return {...prevState, isOpen: true, vet: vetsState.find(v=>v.userName===vetUserId)};
        });
      });
    }
  }

  const onModalClose = () => {
    setModalState((prevState)=>{
      return {...prevState, isOpen: false, vet: undefined};
    });
  }

  const handleModalButtonClick = () => {
    if(timeSelected && modalState?.vet?.userName){
      const appointmentObj = {
        date: dateSelected.format("DD-MM-YYYY"),
        startTime: timeSelected.split("-")[0],
        endTime: timeSelected.split("-")[1],
        vetUserId: modalState.vet.userName,
        animalId: petSelected?.value
      };

      bookAppointment(appointmentObj).then(()=>{
        setToast({ type: 'success', message: 'Appointment is requested, please wait for confirmation email from the vet' });
        setModalState((prevState)=>{
          return {...prevState, isOpen: false, vet: undefined};
        });
        setPetSelected("");
        setDateSelected("");
        setTimeSelected("");
      }).catch(()=>{
        setToast({ type: 'error', message: 'There was an error booking the appointment, please try again' });
        setModalState((prevState)=>{
          return {...prevState, isOpen: false, vet: undefined};
        });
        setPetSelected("");
        setDateSelected("");
        setTimeSelected("");
      });
    }
  }

  const handleDateChange = async (newValue) => {
    const selectedDate = newValue?.format("DD-MM-YYYY") || "";
    if(modalState.vet){
      const response = await getVetAvailabilityOnSpecificDay(selectedDate, modalState.vet.userName);
      setDateOptions(response.slots.map(slot=>({value:`${slot.first}-${slot.second}`})));
    }
    setDateSelected(newValue);
  }

  const render = () => {
    if (isLoading) {
      return <Loader />;
    }

    if (vetsState?.length === 0) {
      return <EmptyState text={'No Vets Available!'} />;
    }

    return <VetCardList vets={vetsState} handleBookClick={handleBookClick}/>;
  };

  return (
    <>
      <div className={classes.root}>{render()}</div>
      <Modal
        open={modalState.isOpen}
        onClose={onModalClose}>
          <div className={classes.paper}>
            <h3 id="simple-modal-title">
              You&apos;re booking appointment with: {modalState.vet?.firstName + " " + modalState.vet?.lastName || ""}
            </h3>
            <div>
              <div className={classes.label}>Select the pet:</div>
              <Select
                  type="text"
                  value={petSelected.value}
                  onChange={(event) =>
                    {
                      const petOp = petOptions.find(petOption => event.target.value === petOption.value);
                      setPetSelected(petOp);
                    }
                  }
                  fullWidth={true}
                  options={petOptions}
                  multiple={false}
                  className={classes.dropdown}
                />
            </div>
            <LocalizationProvider dateAdapter={AdapterDayjs} >
              <div className={classes.label}>Select Date: </div> <DatePicker value={dateSelected} 
              onChange={handleDateChange} />
            </LocalizationProvider>
            <div>
              <div className={classes.label}>Select time:</div>
              <Select
                  type="text"
                  value={timeSelected}
                  onChange={(event) =>
                    setTimeSelected(event.target.value)
                  }
                  fullWidth={true}
                  options={dateOptions}
                  multiple={false}
                  className={classes.dropdown}
                />
            </div>
            <div className={classes.buttonContainer}>
              <Button variant="contained" color="secondary" onClick={onModalClose}>
                Cancel
              </Button>
              <Button
                variant="contained"
                color="primary"
                onClick={handleModalButtonClick}
                className={classes.button}
              >
                Book Appointment
              </Button>
            </div>
          </div>
      </Modal>
    </>
  );
};

export default PetOwnerAllVets;

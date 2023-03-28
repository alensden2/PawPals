/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck

// Import React and necessary Material UI compone nts
import React, { useContext, useEffect, useState } from 'react';
import { Grid } from '@material-ui/core';
import { AddBox } from '@material-ui/icons';

// Import context, components, and data
import { HeaderContext, ToastContext } from '@src/context';
import { Button, DeleteDialog, Loader, EmptyState } from '@src/components';

// Import styles and internal components
import useStyles from './PetOwnerManagePets.styles';
import PetCardList from './PetCardList';
import AddEditPetModal from './AddEditPetModal';

import { createPet, getAllPets, updatePet, deletePet } from '@src/api';

// Define PetOwnerManagePets component
const PetOwnerManagePets = () => {
  const classes = useStyles();
  const { setToast } = useContext(ToastContext);

  // Define initial state for pet data and modal/dialog states
  const initialPetData = {
    id: null,
    name: null,
    type: null,
    age: null,
    gender: null,
    photoUrl: null
  };
  const [pets, setPets] = useState([]);
  const [petModalState, setPetModalState] = useState({
    data: initialPetData,
    isOpen: false,
    modalMode: 'add'
  });
  const [deleteDialogState, setDeleteDialogState] = useState({
    isOpen: false,
    data: initialPetData
  });
  const [isLoading, setIsLoading] = useState(false);

  // Set header context with title, back button, and logout button
  const { setHeader } = useContext(HeaderContext);
  useEffect(() => {
    setHeader({
      shouldShowHeader: true,
      title: 'Manage Pets',
      backRoute: '/pet-owner/home',
      shouldShowLogoutButton: true,
      shouldShowBackButton: true
    });

    async function fetchData() {
      try {
        setIsLoading(true);
        const response = await getAllPets();
        setIsLoading(false);

        setPets(response);
      } catch (e) {
        console.error(e);
      }
    }
    fetchData();

    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  // Define toggle functions for modals and dialogs
  const openEditModal = (pet) => {
    setPetModalState((prevState) => ({
      ...prevState,
      isOpen: true,
      modalMode: 'edit',
      data: pet
    }));
  };
  const openAddPetModal = () => {
    setPetModalState((prevState) => ({
      ...prevState,
      isOpen: true,
      modalMode: 'add',
      data: initialPetData
    }));
  };
  const openDeleteDialog = (pet) => {
    setDeleteDialogState((prevState) => ({
      ...prevState,
      isOpen: true,
      data: pet
    }));
  };
  const closeDeleteDialog = () => {
    setDeleteDialogState((prevState) => ({
      ...prevState,
      isOpen: false
    }));
  };
  const closeModal = () => {
    setPetModalState((prevState) => ({
      ...prevState,
      isOpen: false
    }));
  };

  // Define action functions for adding, editing, and deleting pets
  const onModalSubmitClick = async () => {
    if (petModalState.modalMode === 'edit') {
      const updateInput = {
        name: petModalState.data.name,
        type: petModalState.data.type,
        age: petModalState.data.age,
        gender: petModalState.data.gender,
        photoUrl: petModalState.data.photoUrl
      };

      await updatePet({
        input: updateInput,
        petId: petModalState.data.id
      });

      setPets((prevState) => {
        return prevState.map((pet) =>
          pet.id === petModalState.data.id ? petModalState.data : pet
        );
      });
    }

    if (petModalState.modalMode === 'add') {
      const response = await createPet({
        input: petModalState.data
      });

      if (response.isSuccess) {
        setPets((prevState) => {
          return [
            ...prevState,
            {
              ...response.data
            }
          ];
        });
      } else {
        setToast({ type: 'error', message: 'Something went wrong!' });
      }
    }
    setPetModalState((prevState) => ({
      ...prevState,
      isOpen: false
    }));
  };

  const onDeleteDialogClick = async () => {
    const isSuccess = await deletePet({
      petId: deleteDialogState.data.id
    });

    if (isSuccess) {
      setPets((prevState) => {
        return prevState.filter((pet) => pet.id !== deleteDialogState.data.id);
      });
    } else {
      setToast({ type: 'error', message: 'Something went wrong!' });
    }

    setDeleteDialogState((prevState) => ({
      ...prevState,
      isOpen: false
    }));
  };

  const render = () => {
    if (isLoading) {
      return <Loader />;
    }

    if (pets?.length === 0) {
      return <EmptyState text={'No Pets Added!'} />;
    }

    return (
      <Grid container spacing={2} className={classes.grid}>
        <PetCardList
          pets={pets}
          onEditClick={openEditModal}
          onDeleteClick={openDeleteDialog}
        />
      </Grid>
    );
  };

  // The component renders a Grid with a Button to add new pets, a PetCardList to display all the pets, and two dialogs.
  // The first dialog is the AddEditPetModal, which is used to add or edit pet data.
  // The second dialog is the DeleteDialog, which is used to confirm the deletion of a pet.
  return (
    <div className={classes.root}>
      <div className={classes.addPetRow}>
        <Grid container spacing={2} className={classes.grid}>
          <div className={classes.buttonContainer}>
            <div className={classes.emptyDivContainer} />
            <Button
              type="submit"
              variant="contained"
              color="inherit"
              fullWidth={true}
              className={classes.addPetButton}
              onClick={openAddPetModal}
              startIcon={<AddBox />}
            >
              Add Pet
            </Button>
          </div>
        </Grid>
      </div>
      {render()}
      {petModalState.isOpen ? (
        <AddEditPetModal
          handleSubmit={onModalSubmitClick}
          handleClose={closeModal}
          setPetModalState={setPetModalState}
          petModalState={petModalState}
        />
      ) : null}
      <DeleteDialog
        isOpen={deleteDialogState.isOpen}
        onCloseDialogClick={closeDeleteDialog}
        onDeleteDialogClick={onDeleteDialogClick}
      />
    </div>
  );
};

export default PetOwnerManagePets;

// Import React and necessary Material UI components
import React, { useContext, useEffect, useState } from 'react';
import { Grid } from '@material-ui/core';
import { AddBox } from '@material-ui/icons';

// Import context, components, and data
import { HeaderContext } from '@src/context';
import { Button, DeleteDialog } from '@src/components';
import { petsData } from '@src/data';

// Import styles and internal components
import useStyles from './PetOwnerManagePets.styles';
import PetCardList from './PetCardList';
import AddEditPetModal from './AddEditPetModal';

// Define types
import { PetModalState, DeleteDialogState, PetsState, Pet } from './type';

// Define PetOwnerManagePets component
const PetOwnerManagePets: React.FC = () => {
  const classes = useStyles();

  // Define initial state for pet data and modal/dialog states
  const initialPetData = {
    id: null,
    name: null,
    type: null,
    age: null,
    gender: null,
    photoUrl: null
  };
  const [petsState, setPetsState] = useState<PetsState>(petsData);
  const [petModalState, setPetModalState] = useState<PetModalState>({
    data: initialPetData,
    isOpen: false,
    modalMode: 'add'
  });
  const [deleteDialogState, setDeleteDialogState] = useState<DeleteDialogState>(
    { isOpen: false, data: initialPetData }
  );

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
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  // Define toggle functions for modals and dialogs
  const openEditModal = (pet: Pet) => {
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
  const openDeleteDialog = (pet: Pet) => {
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
  const onModalSubmitClick = () => {
    if (petModalState.modalMode === 'edit') {
      updatePet();
    }
    if (petModalState.modalMode === 'add') {
      addPet();
    }
    setPetModalState((prevState) => ({
      ...prevState,
      isOpen: false
    }));
  };

  const onDeleteDialogClick = () => {
    deletePet();

    setDeleteDialogState((prevState) => ({
      ...prevState,
      isOpen: false
    }));
  };

  // ----------- CRUD functions -----------
  const updatePet = () => {
    setPetsState((prevState) => ({
      pets: prevState.pets.map((pet) =>
        pet.id === petModalState.data.id ? petModalState.data : pet
      )
    }));
  };

  const addPet = () => {
    setPetsState((prevState) => ({
      pets: [
        ...prevState.pets,
        {
          ...petModalState.data,
          id: Math.floor(Math.random() * 1000) + 1
        }
      ]
    }));
  };

  const deletePet = () => {
    setPetsState((prevState) => ({
      pets: prevState.pets.filter((pet) => pet.id !== deleteDialogState.data.id)
    }));
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
      <Grid container spacing={2} className={classes.grid}>
        <PetCardList
          pets={petsState.pets}
          onEditClick={openEditModal}
          onDeleteClick={openDeleteDialog}
        />
      </Grid>
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

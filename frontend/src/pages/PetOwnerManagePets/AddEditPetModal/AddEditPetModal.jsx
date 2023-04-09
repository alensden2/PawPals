// @ts-nocheck

import React from 'react';
import Modal from '@material-ui/core/Modal';
import {
  Button,
  TextField,
  TextField as MuiTextField
} from '@material-ui/core';
import useStyles from './AddEditPetModal.styles';
const AddEditPetModal = ({
  petModalState,
  setPetModalState,
  handleClose,
  handleSubmit
}) => {
  const classes = useStyles();

  // Function to update state when a form input changes
  const handleChange = (event) => {
    const { name, value } = event.target;

    if (event.target.files && event.target.files.length) {
      setPetModalState({
        ...petModalState,
        data: {
          ...petModalState.data,
          [name]: event.target.files[0]
        }
      });
    } else {
      setPetModalState({
        ...petModalState,
        data: {
          ...petModalState.data,
          [name]: value
        }
      });
    }
  };

  // Function to close modal when cancel is clicked
  const handleCancel = () => {
    handleClose();
  };

  // Function to save the pet data and close modal
  const handleSave = () => {
    handleSubmit(petModalState.data);
  };

  return (
    <Modal
      open={petModalState.isOpen}
      onClose={handleClose}
      aria-labelledby="simple-modal-title"
      aria-describedby="simple-modal-description"
    >
      <div className={classes.paper}>
        <h2 id="simple-modal-title">
          {petModalState.modalMode === 'add' ? 'Add Pet' : 'Edit Pet'}
        </h2>
        <form>
          <TextField
            fullWidth
            label="Pet Name"
            name="name"
            value={petModalState.data.name || ''}
            onChange={handleChange}
            className={classes.textField}
          />
          <TextField
            fullWidth
            label="Pet Type"
            name="type"
            value={petModalState.data.type || ''}
            onChange={handleChange}
            className={classes.textField}
          />
          <TextField
            fullWidth
            label="Pet Age"
            name="age"
            value={petModalState.data.age || ''}
            onChange={handleChange}
            className={classes.textField}
            type="number"
          />
          <TextField
            fullWidth
            label="Pet Gender"
            name="gender"
            value={petModalState.data.gender || ''}
            onChange={handleChange}
            className={classes.textField}
          />
          <MuiTextField
            type="file"
            label="Profile Photo"
            name="photoUrl"
            onChange={(event) => {
              if (event.target.files && event.target.files.length) {
                handleChange(event);
              }
            }}
            inputProps={{
              accept: 'image/*'
            }}
          />
        </form>
        <div className={classes.buttonContainer}>
          <Button variant="contained" color="secondary" onClick={handleCancel}>
            Cancel
          </Button>
          <Button
            variant="contained"
            color="primary"
            onClick={handleSave}
            className={classes.button}
          >
            {petModalState.modalMode === 'add' ? 'Add' : 'Save'}
          </Button>
        </div>
      </div>
    </Modal>
  );
};

export default AddEditPetModal;

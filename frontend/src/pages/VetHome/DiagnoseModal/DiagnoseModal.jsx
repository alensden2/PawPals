// @ts-nocheck

import React, { useState } from 'react';
import Modal from '@material-ui/core/Modal';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import useStyles from './DiagnoseModal.styles';

const DiagnoseModal = ({ isOpen, handleClose, onDiagnoseModalSubmitClick }) => {
  const classes = useStyles();
  const [ailmentName, setAilmentName] = useState('');
  const [prescription, setPrescription] = useState('');
  const [vaccines, setVaccines] = useState('');

  const handleAilmentNameChange = (event) => {
    setAilmentName(event.target.value);
  };

  const handlePrescriptionChange = (event) => {
    setPrescription(event.target.value);
  };

  const handleVaccinesChange = (event) => {
    setVaccines(event.target.value);
  };

  const handleSubmit = async () => {
    await onDiagnoseModalSubmitClick({
      ailmentName,
      prescription,
      vaccines
    });
  };

  return (
    <Modal open={isOpen} onClose={handleClose}>
      <div className={classes.paper}>
        <h2 id="simple-modal-title">Diagnose Details</h2>
        <form className={classes.form}>
          <TextField
            label="Ailment Name"
            className={classes.textField}
            value={ailmentName}
            onChange={handleAilmentNameChange}
            required
          />
          <TextField
            label="Prescription"
            className={classes.textField}
            value={prescription}
            onChange={handlePrescriptionChange}
            multiline
            rows={4}
            required
          />
          <TextField
            label="Vaccines"
            className={classes.textField}
            value={vaccines}
            onChange={handleVaccinesChange}
            required
          />
          <div className={classes.buttonContainer}>
            <Button variant="outlined" color="secondary" onClick={handleClose}>
              Cancel
            </Button>
            <Button variant="outlined" color="primary" onClick={handleSubmit}>
              Submit
            </Button>
          </div>
        </form>
      </div>
    </Modal>
  );
};

export default DiagnoseModal;

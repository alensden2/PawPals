/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck
import React, { useState } from 'react';
import {
  Modal,
  Backdrop,
  Fade,
  Typography,
  Tabs,
  Tab,
  Divider,
  CardMedia,
  IconButton,
  Box
} from '@material-ui/core';
import { CloseOutlined } from '@material-ui/icons';

const medicalRecord = 'This is the medical record tab';

const AppointmentDetailsModal = ({
  appointmentDetailsModal,
  closeAppointmentDetailsModal
}) => {
  const classes = useStyles();
  const [tabIndex, setTabIndex] = useState(0);

  const handleTabChange = (event, newValue) => {
    setTabIndex(newValue);
  };

  const renderPetDetails = () => {
    const { pet } = appointmentDetailsModal.appointment;
    return (
      <div className={classes.petDetailsContainer}>
        <CardMedia
          className={classes.media}
          image={pet.photoUrl}
          title={pet.name}
        />
        <Typography variant="body1" component="p">
          Name: {pet.name}
        </Typography>
        <Divider />
        <Typography variant="body1" component="p">
          Type: {pet.type}
        </Typography>
        <Divider />

        <Typography variant="body1" component="p">
          Gender: {pet.gender}
        </Typography>
        <Divider />

        <Typography variant="body1" component="p">
          Age: {pet.age} years old
        </Typography>
        <Divider />
      </div>
    );
  };

  const renderPetOwnerDetails = () => {
    const { petOwner } = appointmentDetailsModal.appointment;

    return (
      <div className={classes.petDetailsContainer}>
        <Typography variant="body1" component="p">
          Name: {`${petOwner.firstName} ${petOwner.lastName}`}
        </Typography>
        <Divider />
        <Typography variant="body1" component="p">
          Phone No: {petOwner.phoneNo}
        </Typography>
        <Divider />
      </div>
    );
  };

  const renderMedicalRecord = () => {
    return <Typography>{medicalRecord}</Typography>;
  };

  const renderTabPanel = () => {
    switch (tabIndex) {
      case 0:
        return renderPetDetails();
      case 1:
        return renderPetOwnerDetails();
      case 2:
        return renderMedicalRecord();
      default:
        return null;
    }
  };

  return (
    <>
      <Modal
        className={classes.modal}
        open={appointmentDetailsModal.isOpen}
        onClose={closeAppointmentDetailsModal}
        closeAfterTransition
        BackdropComponent={Backdrop}
        BackdropProps={{
          timeout: 500
        }}
      >
        <Fade in={appointmentDetailsModal.isOpen}>
          <div className={classes.paper}>
            <div className={classes.header}>
              <Typography variant="h5" className={classes.modalHeader}>
                {'Appointment Details'}
              </Typography>
              <IconButton onClick={closeAppointmentDetailsModal}>
                <CloseOutlined />
              </IconButton>
            </div>
            <Box display="flex" className={classes.box}>
              <div className={classes.sidebar}>
                <Tabs
                  value={tabIndex}
                  onChange={handleTabChange}
                  orientation="vertical"
                >
                  <Tab label="Pet" classes={{ root: classes.tab }} />
                  <Tab label="Pet Owner" classes={{ root: classes.tab }} />
                  <Tab label="Medical Record" classes={{ root: classes.tab }} />
                </Tabs>
              </div>
              <div className={classes.tabPanel}>
                <div className={classes.tabPanelInnerContainer}>
                  {renderTabPanel()}
                </div>
              </div>
            </Box>
          </div>
        </Fade>
      </Modal>
    </>
  );
};

export default AppointmentDetailsModal;

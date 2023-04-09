/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck
import React from 'react';
import useStyles from './AppointmentCard.styles';
import {
  Card,
  CardContent,
  CardMedia,
  Divider,
  Typography
} from '@material-ui/core';
import { PLACEHOLDER_APPOINTMENT_IMAGE, PET_NAME } from '@src/constants';

const AppointmentCard = ({ appointmentData }) => {
  const classes = useStyles();

  const {
    vet: { firstName: vetFirstName, lastName: vetLastName, email: vetEmail },
    appointment: { startTime, endTime, date },
    animal: {
      photoUrl: animalPhotoUrl,
      name: animalName,
      type: animalType,
      age: animalAge
    }
  } = appointmentData;

  return (
    <Card className={classes.root}>
      {/* Header Section */}
      <div className={classes.headerContainer}>
        <Typography variant="h6" align="center">
          {'Pet Name: ' + animalName.toUpperCase()}
        </Typography>
      </div>

      {/* Image Section */}
      <CardMedia
        className={classes.media}
        image={animalPhotoUrl ? animalPhotoUrl : PLACEHOLDER_APPOINTMENT_IMAGE}
        title={animalName ? animalName : PET_NAME}
      />

      {/* Content Section */}
      <CardContent>
        <Typography variant="body1" color="textSecondary" component="p">
          Appointment Time: {startTime + '-' + endTime}
        </Typography>
        <Divider variant="middle" />
        <Typography variant="body1" color="textSecondary" component="p">
          Appointment Date: {date}
        </Typography>
        <Divider variant="middle" />
        <Typography variant="body1" color="textSecondary" component="p">
          Vet Name: {vetFirstName + ' ' + vetLastName}
        </Typography>
        <Divider variant="middle" />
        <Typography variant="body1" color="textSecondary" component="p">
          Vet Email: {vetEmail}
        </Typography>
        <Divider variant="middle" />
        <Typography variant="body1" color="textSecondary" component="p">
          Pet type: {animalType}
        </Typography>
        <Divider variant="middle" />
        <Typography variant="body1" color="textSecondary" component="p">
          Pet Age: {animalAge}
        </Typography>
        <Divider variant="middle" />
      </CardContent>
    </Card>
  );
};

export default AppointmentCard;

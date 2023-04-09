/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck

import React from 'react';
import { Grid } from '@material-ui/core';
import useStyles from './AppointmentsCardList.styles';
import AppointmentCard from '../AppointmentCard';

const AppointmentsCardList = ({ appointments }) => {
  const classes = useStyles();

  return (
    <Grid container spacing={2} className={classes.main}>
      {appointments.map((appointmentData, idx) => (
        <Grid
          item
          xs={12}
          sm={6}
          key={idx}
          className={classes.appointmentCardContainer}
        >
          <AppointmentCard key={idx} appointmentData={appointmentData} />
        </Grid>
      ))}
    </Grid>
  );
};

export default AppointmentsCardList;

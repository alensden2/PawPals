/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck

import React from 'react';
import useStyles from './VetCard.styles';
import {
  Card,
  CardContent,
  CardMedia,
  Divider,
  Typography
} from '@material-ui/core';
import { Button } from '@src/components';
import { PLACEHOLDER_VET_CLINIC_IMAGE } from '@src/constants';

const VetCard = ({
  name,
  phoneNo,
  email,
  experience,
  licenseNumber,
  qualification,
  clinicAddress,
  clinicUrl
}) => {
  const classes = useStyles();

  return (
    <Card className={classes.root}>
      {/* Header Section */}
      <div className={classes.headerContainer}>
        <Typography variant="h6" align="center">
          {name?.toUpperCase()}
        </Typography>

        {/* Action Buttons */}
        <div className={classes.actionButtons}>
          {/* Book appointment button */}
          <Button
            type="submit"
            variant="outlined"
            color="inherit"
            fullWidth={true}
          >
            Book Appointment
          </Button>
        </div>
      </div>

      {/* Image Section */}
      <CardMedia
        className={classes.media}
        image={clinicUrl ? clinicUrl : PLACEHOLDER_VET_CLINIC_IMAGE}
        title={name}
      />

      {/* Content Section */}
      <CardContent>
        <Typography variant="body1" color="textSecondary" component="p">
          Phone No: {phoneNo}
        </Typography>
        <Divider variant="middle" />
        <Typography variant="body1" color="textSecondary" component="p">
          Email: {email}
        </Typography>
        <Divider variant="middle" />
        <Typography variant="body1" color="textSecondary" component="p">
          Experience: {experience} years
        </Typography>
        <Divider variant="middle" />
        <Typography variant="body1" color="textSecondary" component="p">
          License Number: {licenseNumber}
        </Typography>
        <Divider variant="middle" />
        <Typography variant="body1" color="textSecondary" component="p">
          Qualification: {qualification}
        </Typography>
        <Divider variant="middle" />
        <Typography variant="body1" color="textSecondary" component="p">
          Clinic Address: {clinicAddress}
        </Typography>
        <Divider variant="middle" />
      </CardContent>
    </Card>
  );
};

export default VetCard;

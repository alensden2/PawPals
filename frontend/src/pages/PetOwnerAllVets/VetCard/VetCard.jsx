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

import { getImageUrlFromBytes } from '@src/utils';

const VetCard = ({
  userName,
  firstName,
  lastName,
  phoneNo,
  email,
  experience,
  licenseNumber,
  qualification,
  clinicAddress,
  clinicUrl,
  handleBookClick
}) => {
  const classes = useStyles();
  // console.log("userName is: ", userName);
  // console.log("typeof clinicUrl is: ",clinicUrl instanceof Array);
  const clinicUrlB64 = clinicUrl ? getImageUrlFromBytes({ bytes: clinicUrl }) : '';
  // const base64String = btoa(String.fromCharCode(...new Uint8Array(clinicUrl)));
  // console.log("base64",base64String);

  const handleButtonClick = (e) => {
    e.preventDefault();
    handleBookClick(userName);
  }

  return (
    <>
      <Card className={classes.root}>
        {/* Header Section */}
        <div className={classes.headerContainer}>
          <Typography variant="h6" align="center">
            {firstName?.toUpperCase()+" "+lastName?.toUpperCase()}
          </Typography>

          {/* Action Buttons */}
          <div className={classes.actionButtons}>
            {/* Book appointment button */}
            <Button
              type="submit"
              variant="outlined"
              color="inherit"
              fullWidth={true}
              onClick={handleButtonClick}
            >
              Book Appointment
            </Button>
          </div>
        </div>

        {/* Image Section */}
        <CardMedia
          className={classes.media}
          image={clinicUrlB64}
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
    </>
  );
};

export default VetCard;

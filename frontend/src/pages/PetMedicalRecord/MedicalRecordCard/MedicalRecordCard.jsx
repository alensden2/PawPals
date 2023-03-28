/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck

import React from 'react';
import {
  Card,
  CardContent,
  CardHeader,
  Grid,
  Typography,
  Paper,
  Avatar,
  Box
} from '@material-ui/core';
import HEALTH_CARD from '@src/assets/images/health-card.png';
import useStyles from './MedicalRecordCard.styles';

const MedicalHistoryCard = ({ medicalRecord }) => {
  const classes = useStyles();
  const { vet, animal: pet, medicalHistory } = medicalRecord;

  return (
    <div className={classes.root}>
      <Card className={classes.card}>
        <div className={classes.headerContainer}>
          <CardHeader
            titleTypographyProps={{ fontSize: 52 }}
            avatar={
              <Avatar
                src={HEALTH_CARD}
                alt="clinic"
                className={classes.healthCard}
              />
            }
          />
          <Typography variant="h5" className={classes.headerTitle}>
            {`Health Record Card - ${medicalRecord.dateDiagnosed}`}
          </Typography>
        </div>
        <CardContent className={classes.cardContent}>
          <Paper className={classes.subcomponent}>
            <Typography variant="h5" className={classes.title}>
              Pet Info
            </Typography>
            <Grid container alignItems="center">
              <Grid item>
                <Avatar
                  src="pet.jpg"
                  alt="pet"
                  className={classes.petInfoImage}
                />
              </Grid>
              <Grid item>
                <Box ml={3}>
                  <Typography variant="h6" className={classes.infoHeader}>
                    {pet.name}
                  </Typography>
                  <Typography variant="subtitle1" className={classes.infoText}>
                    Type: {pet.type}
                  </Typography>
                  <Typography variant="subtitle1" className={classes.infoText}>
                    Gender: {pet.gender}
                  </Typography>
                  <Typography variant="subtitle1" className={classes.infoText}>
                    Age: {pet.age}
                  </Typography>
                </Box>
              </Grid>
            </Grid>
          </Paper>
          <Paper className={classes.subcomponent}>
            <Typography variant="h5" className={classes.title}>
              Medical Information
            </Typography>
            <Typography variant="subtitle1" className={classes.infoText}>
              Prescription: {medicalHistory.prescription}
            </Typography>
            <Typography variant="subtitle1" className={classes.infoText}>
              Vaccines: {medicalHistory.vaccines}
            </Typography>
          </Paper>
          <Paper className={classes.vetSubComponent}>
            <Typography variant="h5" className={classes.title}>
              Vet Information
            </Typography>
            <Typography variant="subtitle1" className={classes.infoText}>
              Vet Name: {vet.name}
            </Typography>
            <Typography variant="subtitle1" className={classes.infoText}>
              Phone Number: {vet.phoneNo}
            </Typography>
            <Typography variant="subtitle1" className={classes.infoText}>
              Email: {vet.email}
            </Typography>
            <Typography variant="subtitle1" className={classes.infoText}>
              Experience: {vet.experience} years
            </Typography>
            <Typography variant="subtitle1" className={classes.infoText}>
              License Number: {vet.licenseNumber}
            </Typography>
            <Typography variant="subtitle1" className={classes.infoText}>
              Qualification: {vet.qualification}
            </Typography>
            <Typography variant="subtitle1" className={classes.infoText}>
              Clinic Address: {vet.clinicAddress}
            </Typography>
          </Paper>
        </CardContent>
      </Card>
    </div>
  );
};

export default MedicalHistoryCard;

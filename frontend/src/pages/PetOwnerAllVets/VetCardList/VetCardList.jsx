/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck

import React from 'react';
import { Grid } from '@material-ui/core';
import useStyles from './VetCardList.styles';
import VetCard from '../VetCard';

const VetCardList = ({
  // define a functional component PetCardList that receives props pets, onEditClick, and onDeleteClick
  vets
}) => {
  const classes = useStyles();

  return (
    <Grid container spacing={2} className={classes.main}>
      {vets.map((vetData, idx) => (
        <Grid
          item
          xs={12}
          sm={12}
          key={vetData.vetId} // set a unique key to each grid item
          className={classes.petCardContainer}
        >
          <VetCard key={idx} {...vetData} />
        </Grid>
      ))}
    </Grid>
  );
};

export default VetCardList;

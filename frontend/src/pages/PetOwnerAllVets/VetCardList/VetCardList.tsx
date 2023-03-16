import React from 'react';
import { Grid } from '@material-ui/core';
import useStyles from './VetCardList.styles';
import VetCard from '../VetCard';
import { VetList } from '@src/types';

const VetCardList: React.FC<VetList> = ({
  // define a functional component PetCardList that receives props pets, onEditClick, and onDeleteClick
  vets
}) => {
  const classes = useStyles();

  return (
    <Grid container spacing={2} className={classes.main}>
      {vets.map((vetData) => (
        <Grid
          item
          xs={12}
          sm={12}
          key={vetData.vetId} // set a unique key to each grid item
          className={classes.petCardContainer}
        >
          <VetCard key={vetData.vetId} {...vetData} />
        </Grid>
      ))}
    </Grid>
  );
};

export default VetCardList;

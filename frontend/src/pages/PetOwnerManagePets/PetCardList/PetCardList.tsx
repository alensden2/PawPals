import React from 'react';
import { Grid } from '@material-ui/core';
import useStyles from './PetCardList.styles';
import PetCard from '../PetCard';
import { PetListProps } from '../type';

const PetCardList: React.FC<PetListProps> = ({
  // define a functional component PetCardList that receives props pets, onEditClick, and onDeleteClick
  pets,
  onEditClick,
  onDeleteClick
}) => {
  const classes = useStyles();

  return (
    <Grid container spacing={2} className={classes.main}>
      {pets.map(
        (
          petData // iterate through pets array
        ) => (
          <Grid
            item
            xs={12}
            sm={6}
            key={petData.id} // set a unique key to each grid item
            className={classes.petCardContainer}
          >
            <PetCard
              key={petData.id} // set a unique key to each PetCard
              pet={petData}
              onEditClick={onEditClick}
              onDeleteClick={onDeleteClick}
            />
          </Grid>
        )
      )}
    </Grid>
  );
};

export default PetCardList; // export the PetCardList component as the default export

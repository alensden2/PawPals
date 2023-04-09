// @ts-nocheck
import React from 'react';
import useStyles from './PetCard.styles';
import {
  Card,
  CardContent,
  CardMedia,
  Divider,
  IconButton,
  Typography
} from '@material-ui/core';
import { Edit, Delete } from '@material-ui/icons';
// import { PetCardProps } from '../type';
import { PLACEHOLDER_PET_IMAGE, PET_NAME } from '@src/constants';

const PetCard = ({ pet, onEditClick, onDeleteClick }) => {
  const classes = useStyles();

  // Function to handle the edit button click event
  const handleEditClick = () => {
    onEditClick(pet);
  };

  // Function to handle the delete button click event
  const handleDeleteClick = () => {
    onDeleteClick(pet);
  };

  return (
    <Card className={classes.root}>
      {/* Header Section */}
      <div className={classes.headerContainer}>
        <Typography variant="h6" align="center">
          {pet.name?.toUpperCase()}
        </Typography>

        {/* Action Buttons */}
        <div className={classes.actionButtons}>
          <IconButton onClick={handleEditClick} className={classes.button}>
            <Edit />
          </IconButton>
          <IconButton onClick={handleDeleteClick}>
            <Delete />
          </IconButton>
        </div>
      </div>

      {/* Image Section */}
      <CardMedia
        className={classes.media}
        image={pet.photoUrl ? pet.photoUrl : PLACEHOLDER_PET_IMAGE}
        title={pet.name ? pet.name : PET_NAME}
      />

      {/* Content Section */}
      <CardContent>
        <Typography variant="body1" color="textSecondary" component="p">
          Pet Type: {pet.type}
        </Typography>
        <Divider variant="middle" />
        <Typography variant="body1" color="textSecondary" component="p">
          Gender: {pet.gender}
        </Typography>
        <Divider variant="middle" />
        <Typography variant="body1" color="textSecondary" component="p">
          Age: {pet.age} years old
        </Typography>
        <Divider variant="middle" />
      </CardContent>
    </Card>
  );
};

export default PetCard;

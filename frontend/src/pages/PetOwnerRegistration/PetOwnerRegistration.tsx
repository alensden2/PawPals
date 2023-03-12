import React, { useState } from 'react';
import { Container, Link, IconButton, Typography } from '@material-ui/core';
import ArrowBackIosIcon from '@material-ui/icons/ArrowBackIos';
import { Link as RouterLink, useNavigate } from 'react-router-dom';
import { TextField, Button } from '@src/components';
import useStyles from './PetOwnerRegistration.styles';

const PetOwnerRegistration: React.FC = () => {
  const classes = useStyles();
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [phoneNumber, setPhoneNumber] = useState('');
  const [photourl, setPhotourl] = useState('');
  const navigate = useNavigate();

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
  };

  const onBackClick = () => {
    navigate('/');
  };

  return (
    <div>
      <div onClick={onBackClick}>
        <IconButton>
          <ArrowBackIosIcon className="back-button-icon" />
        </IconButton>
        <Button color="inherit">Paw pals</Button>
      </div>
      <Container maxWidth="xs" className={classes.root}>
        <Typography variant="h4" align="center" gutterBottom>
          Pet Owner Registration
        </Typography>
        <form onSubmit={handleSubmit}>
          <TextField
            label="First Name"
            type="text"
            value={firstName}
            onChange={(event) => setFirstName(event.target.value)}
            fullWidth={true}
          />
          <TextField
            label="Last Name"
            type="lastName"
            value={lastName}
            onChange={(event) => setLastName(event.target.value)}
            fullWidth={true}
          />

          <TextField
            label="Phone Number"
            type="phoneNum"
            value={phoneNumber}
            onChange={(event) => setPhoneNumber(event.target.value)}
            fullWidth={true}
          />
           <TextField
            label="Photo URL"
            type="photourl"
            value={photourl}
            onChange={(event) => setPhotourl(event.target.value)}
            fullWidth={true}
          />
          <Button
            type="submit"
            variant="contained"
            color="primary"
            disabled={!firstName || !lastName || !phoneNumber}
            fullWidth={true}
            className={classes.submitButton}
          >
            Sign In
          </Button>
        </form>
        <Typography variant="body2" align="center">
          {"Don't have an account? "}
          <Link component={RouterLink} to="/signup">
            Sign Up
          </Link>
        </Typography>
      </Container>
    </div>
  );
};

export default PetOwnerRegistration;

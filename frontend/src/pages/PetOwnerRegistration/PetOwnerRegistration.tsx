import React, { useState, useContext, useEffect } from 'react';
import { Container, Link, Typography } from '@material-ui/core';
import { Link as RouterLink } from 'react-router-dom';
import { TextField, Button } from '@src/components';
import useStyles from './PetOwnerRegistration.styles';
import { HeaderContext } from '@src/context';

const PetOwnerRegistration: React.FC = () => {
  const classes = useStyles();
  const { setHeader } = useContext(HeaderContext);

  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [phoneNumber, setPhoneNumber] = useState('');
  const [photourl, setPhotourl] = useState('');

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
  };

  useEffect(() => {
    setHeader({
      shouldShowHeader: true,
      title: 'Pet Owner Registration',
      shouldShowLogoutButton: true,
      shouldShowBackButton: true
    });
  }, []);

  return (
    <div>
      <Container maxWidth="xs" className={classes.root}>
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

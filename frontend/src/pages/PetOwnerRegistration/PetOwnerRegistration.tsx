import React, { useState, useContext, useEffect } from 'react';
import { Container } from '@material-ui/core';
import { TextField, Button } from '@src/components';
import useStyles from './PetOwnerRegistration.styles';
import { HeaderContext } from '@src/context';

// hooks
import { useNavigate } from '@src/hooks';

const PetOwnerRegistration: React.FC = () => {
  const classes = useStyles();
  const { setHeader } = useContext(HeaderContext);

  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [phoneNumber, setPhoneNumber] = useState('');

  const navigate = useNavigate();

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    navigate('/pet-owner/home', { replace: true });
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
          <Button
            type="submit"
            variant="contained"
            color="primary"
            disabled={!firstName || !lastName || !phoneNumber}
            fullWidth={true}
            className={classes.submitButton}
          >
            Register
          </Button>
        </form>
      </Container>
    </div>
  );
};

export default PetOwnerRegistration;

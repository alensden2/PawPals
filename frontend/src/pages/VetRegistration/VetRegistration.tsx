import { HeaderContext } from '@src/context';
import React, { ChangeEvent, useContext, useEffect, useState } from 'react';
import useStyles from './VetRegistration.styles';
import { Container, Typography } from '@material-ui/core';
import { TextField, Button, Select } from '@src/components';
import { registerVet } from '@src/api/vet';

// hooks
import { useNavigate } from '@src/hooks';
import { Vet } from '@src/types';

const VetRegistration: React.FC = () => {
  const { setHeader } = useContext(HeaderContext);
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [email, setEmail] = useState('');
  const [phoneNumber, setPhoneNumber] = useState('');
  const [clinicAddress, setClinicAddress] = useState('');
  const [experience, setExperience] = useState(0);
  const [qualifications, setQualifications] = useState([]);
  const [lNumber, setLNumber] = useState('');

  const classes = useStyles();
  const navigate = useNavigate();

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const vet: Omit<Vet, 'vetUserId'> = {
      firstName,
      lastName,
      userName,
      clinicAddress,
      email,
      experience,
      licenseNumber: lNumber,
      phoneNo: phoneNumber,
      qualification: qualifications.join(', ')
    };
    registerVet(vet)
      .then((response) => {
        console.log(response);
      })
      .catch(console.log);
    // navigate('/vet/home', { replace: true });
  };

  const userName: string = 'sankalp_k';

  const qualificationOptions = [
    {
      value: 'MBBS'
    },
    {
      value: 'BMBS'
    },
    {
      value: 'MD'
    },
    {
      value: 'DO'
    }
  ];

  useEffect(() => {
    setHeader({
      shouldShowHeader: true,
      title: 'Vet Registration',
      shouldShowLogoutButton: true,
      shouldShowBackButton: true
    });
  }, []);

  return (
    <div>
      <Typography
        variant="h5"
        align="center"
        color="textSecondary"
        gutterBottom
        className={classes.formLabel}
      >
        Please fill out the information to proceed
      </Typography>
      <Container maxWidth="sm" className={classes.root}>
        <form onSubmit={handleSubmit} className={classes.formContainer}>
          <TextField
            label="Username"
            type="text"
            value={userName}
            onChange={() => {}}
            fullWidth={true}
            nativeProps={{
              disabled: true,
              className: classes.disabledFormField
            }}
          />
          <TextField
            label="First Name"
            type="text"
            value={firstName}
            onChange={(event) => setFirstName(event.target.value)}
            fullWidth={true}
          />
          <TextField
            label="Last Name"
            type="text"
            value={lastName}
            onChange={(event) => setLastName(event.target.value)}
            fullWidth={true}
          />
          <TextField
            label="Email"
            type="text"
            value={email}
            onChange={(event) => setEmail(event.target.value)}
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
            label="Clinic Address"
            type="text"
            value={clinicAddress}
            onChange={(event) => setClinicAddress(event.target.value)}
            fullWidth={true}
          />
          <TextField
            label="Years of Experience"
            type="number"
            value={experience + ''}
            onChange={(event) => setExperience(parseInt(event.target.value))}
            fullWidth={true}
          />
          <TextField
            label="License Number"
            type="text"
            value={lNumber}
            onChange={(event) => setLNumber(event.target.value)}
            fullWidth={true}
          />
          {/* dropdown */}
          <Select
            label="Qualification"
            type="text"
            value={qualifications}
            onChange={(event) => {
              return setQualifications((event.target as any).value);
            }}
            fullWidth={true}
            options={qualificationOptions}
            multiple={true}
            className={classes.dropdown}
          />
          <Button
            type="submit"
            variant="contained"
            color="primary"
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

export default VetRegistration;

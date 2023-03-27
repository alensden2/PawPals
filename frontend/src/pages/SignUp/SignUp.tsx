// react
import React, { useContext, useState } from 'react';
import { Link as RouterLink } from 'react-router-dom';

// material ui
import {
  Container,
  IconButton,
  InputLabel,
  Link,
  MenuItem,
  Select,
  TextField as MuiTextField,
  Typography
} from '@material-ui/core';
import ArrowBackIosIcon from '@material-ui/icons/ArrowBackIos';
import { Select as CustomSelect } from '@src/components';
import { QUALIFICATION_OPTIONS } from '@src/constants/common';
import Backdrop from '@mui/material/Backdrop';
import CircularProgress from '@mui/material/CircularProgress';

// components
import { Button, TextField } from '@src/components';

// styles
import useStyles from './SignUp.styles';

// constants
import { TOAST_MESSAGE_SIGNUP_ERROR, TOAST_MESSAGE_SIGNUP_SUCCESS } from '@src/constants';

// context
import { ToastContext } from '@src/context';

// api
import { registerUser } from '@src/api/auth';
import { RegisterUserType } from '@src/api/type';

// hooks
import { useNavigate } from '@src/hooks';
import { registerVet } from '@src/api/vet';
import { registerPetOwner } from '@src/api/petowner';

const SignUp: React.FC = () => {
  // styles
  const classes = useStyles();

  // state
  const [userName, setUserName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [selectedOption, setSelectedOption] = useState('');
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [phoneNumber, setPhoneNumber] = useState('');
  const [clinicAddress, setClinicAddress] = useState('');
  const [experience, setExperience] = useState(0);
  const [qualifications, setQualifications] = useState([]);
  const [lNumber, setLNumber] = useState('');
  const [clinicPhoto, setClinicPhoto] = useState(null as unknown as File);
  const [profilePhoto, setProfilePhoto] = useState(null as unknown as File);
  const [loader, setLoader] = React.useState(false);

  const handleClose = () => {
    setLoader(false);
  };

  // context
  const { setToast } = useContext(ToastContext);

  // navigation
  const navigate = useNavigate();

  // submit function
  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    setLoader(true);
    //Step 1: register user
    //step 2: register vet/ pet-owner
    const response: RegisterUserType = await registerUser({
      userName,
      password,
      role: selectedOption,
      email
    });

    const hasError = response.error;
    if (hasError) {
      // display error toast
      setToast({ type: 'error', message: response.errorMessage });
    } else {
      let success = false;
      if (selectedOption === 'VET') {
        const response = await registerVet({
          userName,
          firstName,
          lastName,
          clinicAddress,
          experience,
          licenseNumber: lNumber,
          phoneNo: phoneNumber,
          qualification: qualifications.join(', '),
          clinicPhoto,
          email
        });
        success = response === userName;
      } else if (selectedOption === 'PET_OWNER') {
        const response = await registerPetOwner({
          userName,
          firstName,
          lastName,
          address: clinicAddress,
          phoneNo: phoneNumber,
          photoUrl: profilePhoto,
          email
        });
        success = !!response.success && !response.error;
      }
      setLoader(false);
      // display success toast and ask user to sign in
      setToast({
        type: success ? 'success' : 'error',
        message: success
          ? TOAST_MESSAGE_SIGNUP_SUCCESS
          : TOAST_MESSAGE_SIGNUP_ERROR
      });
      if (success) {
        navigate('/signin');
      }
    }
  };

  const handleSelectChange = (event: any) => {
    setSelectedOption(event.target.value);
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
        <Backdrop
          sx={{ color: '#fff', zIndex: (theme) => theme.zIndex.drawer + 1 }}
          open={loader}
          onClick={handleClose}
        >
          <CircularProgress color="inherit" />
        </Backdrop>
        <Typography variant="h4" align="center" gutterBottom>
          Sign Up
        </Typography>
        <form onSubmit={handleSubmit} className={classes.formContainer}>
          <TextField
            label="Username"
            type="text"
            value={userName}
            fullWidth={true}
            onChange={(event) => setUserName(event.target.value)}
          />
          <TextField
            label="Email"
            type="email"
            value={email}
            fullWidth={true}
            onChange={(event) => setEmail(event.target.value)}
          />
          <TextField
            label="Password"
            type="password"
            value={password}
            fullWidth={true}
            onChange={(event) => setPassword(event.target.value)}
          />
          <div className={classes.selectDropdownContainer}>
            <InputLabel>Select Role</InputLabel>
            <Select
              value={selectedOption}
              onChange={handleSelectChange}
              className={classes.selectDropdown}
            >
              <MenuItem value="VET">Vet</MenuItem>
              <MenuItem value="PET_OWNER">Pet Owner</MenuItem>
            </Select>
          </div>
          <div
            className={[
              classes.expandContainer,
              selectedOption ? 'expanded' : 'collapsed',
              selectedOption && selectedOption === 'VET' ? 'vet' : 'petowner'
            ].join(' ')}
          >
            {selectedOption ? (
              <>
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
                  label="Phone Number"
                  type="phoneNum"
                  value={phoneNumber}
                  onChange={(event) => setPhoneNumber(event.target.value)}
                  fullWidth={true}
                />
              </>
            ) : (
              <></>
            )}
            {selectedOption === 'VET' ? (
              <>
                <div>
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
                    onChange={(event) =>
                      setExperience(parseInt(event.target.value))
                    }
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
                  <CustomSelect
                    label="Qualification"
                    type="text"
                    value={qualifications}
                    onChange={(event) =>
                      setQualifications((event.target as any).value)
                    }
                    fullWidth={true}
                    options={QUALIFICATION_OPTIONS}
                    multiple={true}
                    className={classes.dropdown}
                  />
                  <MuiTextField
                    type="file"
                    label="Clinic Photo"
                    onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
                      if (event.target.files && event.target.files.length) {
                        setClinicPhoto(event.target.files[0]);
                      }
                    }}
                    inputProps={{
                      accept: 'image/*'
                    }}
                  />
                </div>
              </>
            ) : selectedOption === 'PET_OWNER' ? (
              <>
                <TextField
                  label="Address"
                  type="text"
                  value={clinicAddress}
                  onChange={(event) => setClinicAddress(event.target.value)}
                  fullWidth={true}
                />
                <MuiTextField
                  type="file"
                  label="Profile Photo"
                  onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
                    if (event.target.files && event.target.files.length) {
                      setProfilePhoto(event.target.files[0]);
                    }
                  }}
                  inputProps={{
                    accept: 'image/*'
                  }}
                />
              </>
            ) : (
              <></>
            )}
          </div>
          <Button
            type="submit"
            variant="contained"
            color="primary"
            disabled={!email || !password}
            fullWidth={true}
            className={classes.submitButton}
          >
            Sign Up
          </Button>
        </form>
        <Typography variant="body2" align="center">
          Already have an account?{' '}
          <Link component={RouterLink} to="/signin">
            Sign In
          </Link>
        </Typography>
      </Container>
    </div>
  );
};

export default SignUp;

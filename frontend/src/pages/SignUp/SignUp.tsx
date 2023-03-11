import React, { useState } from 'react';
import {
  Container,
  Link,
  IconButton,
  Typography,
  Select,
  InputLabel,
  MenuItem
} from '@material-ui/core';
import { Link as RouterLink, useNavigate } from 'react-router-dom';
import useStyles from './SignUp.styles';
import ArrowBackIosIcon from '@material-ui/icons/ArrowBackIos';

import { TextField, Button, Toast } from '@src/components';

import { RegisterUserApiResponse, ToastMessage } from '@src/interfaces';
import { registerUser } from '@src/api/auth';
import { TOAST_MESSAGE_SIGNUP_SUCCESS } from '@src/constants';

const SignUp: React.FC = () => {
  const [toastMessage, setToastMessage] = useState<ToastMessage>({
    type: 'success',
    message: ''
  });

  const classes = useStyles();

  const [userName, setUserName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [selectedOption, setSelectedOption] = useState('');

  const navigate = useNavigate();

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    const response: RegisterUserApiResponse = await registerUser({
      userName,
      password,
      role: selectedOption,
      email
    });

    const hasError = response?.data?.error;
    if (hasError) {
      setToastMessage({ type: 'error', message: response.data?.message });
    } else {
      setToastMessage({
        type: 'success',
        message: TOAST_MESSAGE_SIGNUP_SUCCESS
      });
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
        <Typography variant="h4" align="center" gutterBottom>
          Sign Up
        </Typography>
        <form onSubmit={handleSubmit}>
          <TextField
            label="User name"
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
              <MenuItem value="ROLE_ADMIN">Admin</MenuItem>
            </Select>
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
      <Toast toast={toastMessage} />
    </div>
  );
};

export default SignUp;

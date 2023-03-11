// react
import React, { useState, useContext } from 'react';
import { Link as RouterLink, useNavigate } from 'react-router-dom';

// material ui
import {
  Container,
  Link,
  IconButton,
  Typography,
  Select,
  InputLabel,
  MenuItem
} from '@material-ui/core';
import ArrowBackIosIcon from '@material-ui/icons/ArrowBackIos';

// components
import { TextField, Button } from '@src/components';

// styles
import useStyles from './SignUp.styles';

// constants
import { TOAST_MESSAGE_SIGNUP_SUCCESS } from '@src/constants';

// context
import { ToastContext } from '@src/context';

// api
import { RegisterUserType } from '@src/api/type';
import { registerUser } from '@src/api/auth';

const SignUp: React.FC = () => {
  // styles
  const classes = useStyles();

  // state
  const [userName, setUserName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [selectedOption, setSelectedOption] = useState('');

  // context
  const { setToast } = useContext(ToastContext);

  // navigation
  const navigate = useNavigate();

  // submit function
  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

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
      // display success toast and ask user to sign in
      setToast({
        type: 'success',
        message: TOAST_MESSAGE_SIGNUP_SUCCESS
      });

      navigate('/signin');
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
    </div>
  );
};

export default SignUp;

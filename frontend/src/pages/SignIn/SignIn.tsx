// react
import React, { useState, useContext } from 'react';
import { Link as RouterLink, useNavigate } from 'react-router-dom';

// material ui
import { Container, Link, IconButton, Typography } from '@material-ui/core';
import ArrowBackIosIcon from '@material-ui/icons/ArrowBackIos';

// styles
import useStyles from './SignIn.styles';

// components
import { TextField, Button } from '@src/components';

// api
import { authenticateUser } from '@src/api';
import { AuthenticateUserType } from '@src/api/type';

// constants
import {
  TOAST_MESSAGE_SIGNIN_SUCCESS,
  TOAST_MESSAGE_SIGNIN_FAILURE
} from '@src/constants';

// context
import { ToastContext } from '@src/context';

const SignIn: React.FC = () => {
  // styles
  const classes = useStyles();

  // context
  const { setToast } = useContext(ToastContext);

  // state
  const [userName, setUserName] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  // submit click
  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    const response: AuthenticateUserType = await authenticateUser({
      username: userName,
      password
    });
    console.log(response, 'response');

    const hasError = response.error;
    if (hasError) {
      // display error toast
      setToast({ type: 'error', message: TOAST_MESSAGE_SIGNIN_FAILURE });
    } else {
      // display success toast and ask user to sign in
      setToast({
        type: 'success',
        message: TOAST_MESSAGE_SIGNIN_SUCCESS
      });
    }
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
          Log In
        </Typography>
        <form onSubmit={handleSubmit}>
          <TextField
            label="User Name"
            type="text"
            value={userName}
            onChange={(event) => setUserName(event.target.value)}
            fullWidth={true}
          />
          <TextField
            label="Password"
            type="password"
            value={password}
            onChange={(event) => setPassword(event.target.value)}
            fullWidth={true}
          />
          <Button
            type="submit"
            variant="contained"
            color="primary"
            disabled={!userName || !password}
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

export default SignIn;

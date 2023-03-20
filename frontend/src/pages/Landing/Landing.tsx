import * as React from 'react';
import {
  AppBar,
  Toolbar,
  Typography,
  Link,
  Container,
  Grid,
  Box
} from '@material-ui/core';
import IconButton from '@material-ui/core/IconButton';
import MenuIcon from '@material-ui/icons/Menu';
import PAW_LOGO from '@src/assets/images/paw-black.png';
import { useNavigate } from '@src/hooks';
import useStyles from './Landing.styles';
import { Button } from '@src/components';

const Landing: React.FC = () => {
  const classes = useStyles();
  const navigate = useNavigate();

  const onLoginClick = () => {
    navigate('signin');
  };

  const onSignUpClick = () => {
    navigate('signup');
  };

  const onHomeClick = () => {
    navigate('/');
  };

  const onAboutUsClick = () => {
    navigate('/about-us');
  };

  const onContactUsClick = () => {
    navigate('/contact-us');
  };

  return (
    <>
      <AppBar className={classes.header}>
        <Toolbar>
          <Link href="/" color="inherit" className={classes.logo}>
            <div className={classes.headerTitleContainer}>
              <img
                src={PAW_LOGO}
                alt="Paw Pals Logo"
                className={classes.logo}
                width={28}
                height={28}
              />
              <Typography variant="h5">{'PawPals'}</Typography>
            </div>
          </Link>
          <IconButton
            color="inherit"
            aria-label="open drawer"
            edge="end"
            className={classes.menuButton}
          >
            <MenuIcon />
          </IconButton>
          <nav className={classes.navLinks}>
            <Link
              color="inherit"
              className={classes.navLink}
              onClick={onHomeClick}
            >
              {'Home'}
            </Link>
            <Link
              color="inherit"
              className={classes.navLink}
              onClick={onAboutUsClick}
            >
              {'About Us'}
            </Link>
            <Link
              color="inherit"
              className={classes.navLink}
              onClick={onContactUsClick}
            >
              {'Contact Us'}
            </Link>
            <Button
              variant="outlined"
              color="inherit"
              onClick={onLoginClick}
              className={classes.navLink}
            >
              {'Login'}
            </Button>
            <Button
              variant="outlined"
              color="inherit"
              onClick={onSignUpClick}
              className={classes.navLink}
            >
              {'Sign Up'}
            </Button>
          </nav>
        </Toolbar>
      </AppBar>
    </>
  );
};

export default Landing;

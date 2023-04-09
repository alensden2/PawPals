// @ts-nocheck

import React from 'react';
import { AboutUsStyles } from './AboutUs.styles';
import { AppBar, Toolbar, Typography, Link, Button } from '@material-ui/core';
import PAW_LOGO from '@src/assets/images/paw-black.png';
import { ROLE_TO_ROUTE_MAPPING } from '@src/constants';
import { useNavigate } from '@src/hooks';
import { localStorageUtil } from '@src/utils';

function Navbar() {
  const classes = AboutUsStyles();
  const navigate = useNavigate();

  const onHomeClick = () => {
    navigate('/');
  };

  const onAboutUsClick = () => {
    navigate('/about-us');
  };

  const onLoginClick = () => {
    const user = localStorageUtil.getItem('user');

    if (['VET', 'PET_OWNER'].includes(user?.role)) {
      navigate(ROLE_TO_ROUTE_MAPPING[user?.role], { replace: true });
    } else {
      navigate('/signin', { replace: true });
    }
  };

  const onSignUpClick = () => {
    navigate('/signup', { replace: true });
  };

  return (
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
  );
}

function Hero() {
  const classes = AboutUsStyles();

  return <div className={classes.aboutusHero + ' ' + classes.hero}></div>;
}

function AboutUs() {
  const classes = AboutUsStyles();

  return (
    <section id="about-us" className={classes.aboutUs}>
      <h2 className={classes.aboutUs}>About Us</h2>
      <p>
        {
          "At PawPals, we believe that pets are more than just animals - they are family. That's why we're dedicated to providing the best possible care and services for your furry loved ones."
        }
      </p>
      <p>
        {
          'PawPals is started by a group of college students from Dalhousie University who shared a passion for animals and wanted it to reach to a global level.'
        }
      </p>
      <p>
        {
          "Today, PawPals offers vet booking for Pet Owner at his own time. We pride ourselves on our commitment to quality and our love for animals, and we're proud to be a part of the pet care community."
        }
      </p>
    </section>
  );
}

function Footer() {
  const classes = AboutUsStyles();

  return (
    <footer className={classes.footer}>
      <p>&copy; 2023 PawPals. All rights reserved.</p>
    </footer>
  );
}

function Main() {
  return (
    <>
      <Navbar />
      <Hero />
      <AboutUs />
      <Footer />
    </>
  );
}

export default Main;

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
import { localStorageUtil } from '@src/utils';
import { ROLE_TO_ROUTE_MAPPING } from '@src/constants';

const Landing: React.FC = () => {
  const classes = useStyles();
  const navigate = useNavigate();

  const onLoginClick = () => {
    const user = localStorageUtil.getItem('user');

    if (['VET', 'PET_OWNER'].includes(user?.role)) {
      // eslint-disable-next-line @typescript-eslint/ban-ts-comment
      // @ts-ignore
      navigate(ROLE_TO_ROUTE_MAPPING[user?.role], { replace: true });
    } else {
      navigate('signin');
    }
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
      <Container className={classes.containerClass}>
        <Grid container spacing={3} className={classes.gridContainer}>
          <Grid item xs={12} className={classes.petBackgroundMain}>
            <Typography variant="h1" className={classes.petBackgroundTitle}>
              {'Welcome to Pawpals'}
            </Typography>
            <Typography variant="h4" className={classes.petBackgroundSubtitle}>
              {'Get the Best Care for your Pets with PawPals.'}
            </Typography>
          </Grid>
          <Grid item xs={12} className={classes.care}>
            <Typography variant="h2" className={classes.careTitle}>
              {'We care for your Pets'}
            </Typography>
            <Typography variant="h6" className={classes.careSubtitle}>
              {
                "At PawPals, we understand how much you love your furry friends. That's why we provide the best care possible to ensure they are healthy and happy. Our team of expert veterinarians and pet care professionals are dedicated to making sure your pets receive the attention and care they deserve."
              }
            </Typography>
          </Grid>
          <Grid item xs={12} className={classes.petBackground}>
            <Typography variant="h1" className={classes.bottomImageTitle}>
              {'Your Pet Deserves the Best'}
            </Typography>
            <Typography variant="h4" className={classes.bottomImageSubtitle}>
              {
                'At PawPals, we provide the best care possible to ensure your pets are healthy and happy. Our team of expert veterinarians and pet care professionals are dedicated to making sure your pets receive the attention and care they deserve.'
              }
            </Typography>
          </Grid>
        </Grid>
      </Container>
      <Box className={classes.footer}>
        <Typography variant="body1">
          {'© 2023 PawPals. All rights reserved.'}
        </Typography>
        <nav>
          <Link href="#" color="inherit" className={classes.footerLink}>
            {'Privacy Policy'}
          </Link>
          <Link href="#" color="inherit" className={classes.footerLink}>
            {'Terms of Use'}
          </Link>
          <Link href="#" color="inherit" className={classes.footerLink}>
            {'Sitemap'}
          </Link>
        </nav>
      </Box>
    </>
  );
};

export default Landing;

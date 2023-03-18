import React from 'react';
import { AppBar, IconButton, Toolbar, Typography } from '@material-ui/core';
import { ExitToApp } from '@material-ui/icons';
import useStyles from './Header.styles';
import PAW_LOGO from '@src/assets/images/paw-white.png';
import ArrowBackIosIcon from '@material-ui/icons/ArrowBackIos';
import { useNavigate } from '@src/hooks';

type HeaderProps = {
  title?: string;
  backRoute?: string;
  shouldShowBackButton?: boolean;
  shouldShowLogoutButton?: boolean;
};

const Header: React.FC<HeaderProps> = ({
  title,
  backRoute = '',
  shouldShowBackButton,
  shouldShowLogoutButton
}) => {
  const classes = useStyles();
  const navigate = useNavigate();

  const handleBack = () => {
    navigate(backRoute, { replace: true });
  };

  return (
    <AppBar position="fixed" className={classes.appBar}>
      <Toolbar>
        {shouldShowBackButton ? (
          <IconButton
            edge="start"
            color="inherit"
            aria-label="back"
            className={classes.clickable}
            onClick={handleBack}
          >
            <ArrowBackIosIcon className="back-button-icon" />
          </IconButton>
        ) : null}
        <div>
          <img
            src={PAW_LOGO}
            alt="Paw Pals Logo"
            className={classes.logo}
            width={28}
            height={28}
          />
        </div>
        <Typography variant="h5" className={classes.title}>
          {title}
        </Typography>
        <div className={classes.grow}></div>
        {shouldShowLogoutButton ? (
          <>
            <Typography
              variant="body1"
              className={classes.clickable}
              //   onClick={onLogoutClick}
            >
              Logout
            </Typography>
            <IconButton
              color="inherit"
              className={classes.clickable}
              //   onClick={onLogoutClick}
            >
              <ExitToApp />
            </IconButton>
          </>
        ) : null}
      </Toolbar>
    </AppBar>
  );
};

export default Header;

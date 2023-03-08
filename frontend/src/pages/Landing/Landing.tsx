import React from 'react';
import ChevronRightIcon from '@material-ui/icons/ChevronRight';
import { useNavigate } from 'react-router-dom';
import useStyles from './Landing.styles';

const HOME_PAGE_IMAGE = '../../assets/images/homepage.jpg';

const Landing: React.FC = () => {
  const classes = useStyles();
  const navigate = useNavigate();

  const onLoginClick = () => {
    navigate('/signin');
  };

  const onSignUpClick = () => {
    navigate('/signup');
  };

  return (
    <div className={classes.container}>
      <div className={classes.leftContainer}>
        <img src={HOME_PAGE_IMAGE} alt="Product" className={classes.image} />
      </div>
      <div className={classes.rightContainer}>
        <div>
          <h1 className={classes.pawpalsText}>Paw Pals</h1>
        </div>
        <div className={classes.buttonContainer} onClick={onLoginClick}>
          <div style={{ fontFamily: 'Oswald' }} className={classes.loginText}>
            Sign In
          </div>
          <ChevronRightIcon />
        </div>
        <div className={classes.buttonContainer} onClick={onSignUpClick}>
          <div style={{}} className={classes.signUpText}>
            Sign Up
          </div>
          <ChevronRightIcon />
        </div>
      </div>
    </div>
  );
};

export default Landing;

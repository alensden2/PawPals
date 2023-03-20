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


  return (
    <>
    </>
  );
};

export default Landing;

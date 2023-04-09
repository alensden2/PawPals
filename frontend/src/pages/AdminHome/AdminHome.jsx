// @ts-nocheck

import React, { useContext, useEffect } from 'react';
import { HeaderContext } from '@src/context';
import { Card, CardMedia, CardContent, Typography } from '@material-ui/core';
import useStyles from './AdminHome.styles';
import { useNavigate } from '@src/hooks';
import { ADMIN_HOME_PAGE_CARDS } from '@src/constants';

const AdminHome = () => {
  const { setHeader } = useContext(HeaderContext);
  const classes = useStyles();
  const navigate = useNavigate();

  useEffect(() => {
    setHeader({
      shouldShowHeader: true,
      title: 'Admin Home',
      shouldShowLogoutButton: true,
      shouldShowBackButton: true
    });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const onCardClick = (route) => {
    navigate(route, { replace: true });
  };

  return (
    <div className={classes.root}>
      <div className={classes.innerContainer}>
        {ADMIN_HOME_PAGE_CARDS.map((item) => {
          return (
            <Card
              className={classes.card}
              key={item.key}
              onClick={() => onCardClick(item.route)}
            >
              <CardMedia
                component="img"
                height="160"
                image={item.image}
                alt={item.altImage}
              />
              <CardContent className={classes.content}>
                <Typography gutterBottom variant="h5" component="div">
                  {item.title}
                </Typography>
                <Typography variant="body2">{item.description}</Typography>
              </CardContent>
            </Card>
          );
        })}
      </div>
    </div>
  );
};

export default AdminHome;

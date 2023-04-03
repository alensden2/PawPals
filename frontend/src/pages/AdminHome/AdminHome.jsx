/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck

import React, { useContext, useEffect } from 'react';
import { HeaderContext } from '@src/context';
import { Card, CardMedia, CardContent, Typography } from '@material-ui/core';
import useStyles from './AdminHome.styles';
import APPROVE_REJECT from '@src/assets/images/admin_approve_reject.png';
import VETS from '@src/assets/images/admin_vets.jpg';
import PET_OWNER from '@src/assets/images/admin_pet_owners.jpg';
import { useNavigate } from '@src/hooks';

const ADMIN_HOME_CONST = [
  {
    key: 1,
    image: APPROVE_REJECT,
    altImage: '"Manage Vets',
    title: 'Manage Vets',
    description: 'Approve or reject veterinarian.',
    route: '/admin/manage-vets'
  }
  // {
  //   key: 2,
  //   image: VETS,
  //   altImage: 'All Vets',
  //   title: 'All Vets',
  //   description: 'Show a list of all veterinarians',
  //   route: 'all-vets'
  // },
  // {
  //   key: 3,
  //   image: PET_OWNER,
  //   altImage: 'All Pet Owners',
  //   title: 'All Pet Owners',
  //   description: 'Display a list of all pet owners',
  //   route: 'all-pet-owners'
  // }
];

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
        {ADMIN_HOME_CONST.map((item) => {
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

/* eslint-disable react/no-unescaped-entities */
import React, { useContext, useEffect } from 'react';
import { Grid, Paper, Card, CardContent, Typography } from '@material-ui/core';
import { HeaderContext } from '@src/context';
import useStyles from './PetOwnerHome.styles';
import { Pets, History, Book, Info, ContactSupport } from '@material-ui/icons';
import { PET_OWNER_HOME_PAGE_CARDS } from '@src/constants';
import { useNavigate } from '@src/hooks';

interface CardProps {
  uid: string;
  title: string;
  color: string;
  route: string;
  onCardClick: (route: string) => void;
}

function CustomCard({ title, color, uid, route, onCardClick }: CardProps) {
  const classes = useStyles();

  const getIcon = () => {
    switch (uid) {
      case 'MANAGE_PETS':
        return <Pets className={classes.icon} />;
      case 'MEDICAL_HISTORY':
        return <History className={classes.icon} />;
      case 'BOOK_APPOINTMENT_WITH_VET':
        return <Book className={classes.icon} />;
      case 'HEALTH_AND_DISEASE':
        return <Info className={classes.icon} />;
      case 'SUPPORT':
        return <ContactSupport className={classes.icon} />;
      default:
        return null;
    }
  };

  return (
    <Card
      className={classes.card}
      style={{ background: color }}
      onClick={() => onCardClick(route)}
    >
      <CardContent className={classes.cardContent}>
        {getIcon()}
        <Typography className={classes.title}>{title}</Typography>
      </CardContent>
    </Card>
  );
}

const PetOwnerHome: React.FC = () => {
  const classes = useStyles();
  const { setHeader } = useContext(HeaderContext);
  const navigate = useNavigate();

  useEffect(() => {
    setHeader({
      shouldShowHeader: true,
      title: 'Home',
      shouldShowLogoutButton: true,
      shouldShowBackButton: true
    });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const onCardClick = (route: string) => {
    navigate(route, { replace: true });
  };

  return (
    <div className={classes.root}>
      <Grid container spacing={3} className={classes.grid}>
        <Grid item xs={12} md={4} className={classes.paperContainerGrid}>
          <Paper className={`${classes.paper} ${classes.leftContainer}`}>
            <img
              className={classes.image}
              src="https://via.placeholder.com/200x200.png?text=Paw+Pals+Logo"
              alt="Company Logo"
            />
            <Typography variant="body1" gutterBottom>
              Paw pals aims to make it easier for you to connect with vets and
              manage your pet's health. With our application, you can access
              important information such as your pet's medical history and
              common symptoms right from your device. We understand how
              important it is to keep your pet healthy and happy, and that's why
              we've made it convenient for you to book appointments with vets
              directly through our app. Our platform is user-friendly and
              designed to help you collaborate and communicate with vets. We
              want to ensure that your pet's overall health and well-being are
              improved, and we are committed to providing you with the best
              service possible.
            </Typography>
          </Paper>
        </Grid>
        <div className={classes.rightContainer}>
          {PET_OWNER_HOME_PAGE_CARDS.map((card) => {
            return (
              <CustomCard
                key={card.uid}
                uid={card.uid}
                title={card.title}
                color={card.color}
                route={card.route}
                onCardClick={onCardClick}
              />
            );
          })}
        </div>
      </Grid>
    </div>
  );
};

export default PetOwnerHome;

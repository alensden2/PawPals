/* eslint-disable react/no-unescaped-entities */
import React, { useContext, useEffect } from 'react';
import { Grid, Paper, Card, CardContent, Typography } from '@material-ui/core';
import { HeaderContext } from '@src/context';
import useStyles from './PetOwnerHome.styles';

interface CardProps {
  title: string;
  color: string;
}

function CustomCard({ title, color }: CardProps) {
  const classes = useStyles();

  return (
    <Card className={classes.card} style={{ background: color }}>
      <CardContent>
        <Typography className={classes.title}>{title}</Typography>
      </CardContent>
    </Card>
  );
}

const PetOwnerHome: React.FC = () => {
  const classes = useStyles();
  const { setHeader } = useContext(HeaderContext);

  useEffect(() => {
    setHeader({
      shouldShowHeader: true,
      title: 'Home',
      shouldShowLogoutButton: true,
      shouldShowBackButton: true
    });
  }, []);

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
          <CustomCard title="Manage Pets" color="#5F758E" />
          <CustomCard title="Medical History" color="#4d7c8a" />
          <CustomCard title="Book appointment with Vet" color="#7f9c96" />
          <CustomCard
            title="Learn about Pet health and diseases"
            color="#8fad88"
          />
          <CustomCard title="Support/ Contact Us" color="#cbdf90" />
        </div>
      </Grid>
    </div>
  );
};

export default PetOwnerHome;

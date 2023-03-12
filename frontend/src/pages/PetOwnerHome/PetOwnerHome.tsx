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
              Our company is dedicated to making it easier for pet owners and
              vets to connect and manage pet health. Our application allows pet
              owners to manage their pets health through their phone or
              computer, giving them easy access to important information such as
              medical history and common symptoms. Additionally, pet owners can
              book appointments with vets directly through the app, making the
              process more convenient and streamlined. Our goal is to improve
              the overall health and well-being of pets by providing a
              user-friendly platform for pet owners and vets to collaborate and
              communicate.
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

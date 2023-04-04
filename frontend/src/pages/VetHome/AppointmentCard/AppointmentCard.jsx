/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck
import {
  Card,
  CardMedia,
  CardContent,
  Typography,
  Button,
  CardActions,
  Divider
} from '@mui/material';
import useStyles from './AppointmentCard.styles';
import { VET_HOME_APPOINTMENT_CONSTANT } from '@src/constants';

const AppointmentCard = ({
  appointment,
  openAppointmentDetailsModal,
  onDeclineAppointmentClick,
  onDiagnoseButtonClick,
  onApproveAppointmentClick
}) => {
  const classes = useStyles();
  const status = appointment.appointment.status;

  const renderApproveDeclineButtons = () => {
    return (
      <>
        <Button
          size="small"
          color="success"
          className={classes.button}
          fullWidth
          variant="outlined"
          onClick={() =>
            onApproveAppointmentClick({
              appointmentId: appointment.appointment.id
            })
          }
        >
          Approve
        </Button>
        <Button
          size="small"
          color="error"
          className={classes.button}
          fullWidth
          variant="outlined"
          onClick={() =>
            onDeclineAppointmentClick({
              appointmentId: appointment.appointment.id
            })
          }
        >
          Decline
        </Button>
      </>
    );
  };

  const renderDiagnoseButton = () => {
    return (
      <>
        <Button
          size="small"
          color="primary"
          className={classes.button}
          fullWidth
          variant="outlined"
          onClick={() =>
            onDiagnoseButtonClick({
              petId: appointment.pet.id,
              appointmentId: appointment.appointment.id,
              openModal: true
            })
          }
        >
          Diagnose
        </Button>
      </>
    );
  };

  const renderViewDetailsButton = () => {
    return (
      <>
        <Button
          size="small"
          color="secondary"
          className={classes.button}
          fullWidth
          variant="outlined"
          onClick={() => openAppointmentDetailsModal({ appointment })}
        >
          View Details
        </Button>
      </>
    );
  };

  const renderInfoButton = () => {
    return (
      <>
        <Button
          size="small"
          color="inherit"
          className={classes.button}
          fullWidth
          variant="outlined"
          onClick={() => openAppointmentDetailsModal({ appointment })}
        >
          Info
        </Button>
      </>
    );
  };

  return (
    <Card className={classes.root}>
      {/* Image Section */}
      <CardMedia
        className={classes.media}
        image={appointment.pet.photoUrl}
        title={appointment.pet.name}
      />

      {/* Content Section */}
      <CardContent>
        <Typography variant="body1" color="textSecondary" component="p">
          Pet Name: {appointment.pet.name}
        </Typography>
        <Divider variant="middle" />
        <Typography variant="body1" color="textSecondary" component="p">
          Appointment Date: {appointment.appointment.date}
        </Typography>
        <Divider variant="middle" />
        <Typography variant="body1" color="textSecondary" component="p">
          Appointment Time:{' '}
          {`${appointment.appointment.startTime} - ${appointment.appointment.endTime}`}
        </Typography>
        <Divider variant="middle" />
      </CardContent>

      {/* Action Buttons */}
      <CardActions>
        {VET_HOME_APPOINTMENT_CONSTANT[status].shouldShowApproveDeclineButtons
          ? renderApproveDeclineButtons()
          : null}
        {VET_HOME_APPOINTMENT_CONSTANT[status].shouldShowDiagnoseButton
          ? renderDiagnoseButton()
          : null}
        {VET_HOME_APPOINTMENT_CONSTANT[status].shouldShowViewDetailsButton
          ? renderViewDetailsButton()
          : null}
        {VET_HOME_APPOINTMENT_CONSTANT[status].shouldShowInfoButton
          ? renderInfoButton()
          : null}
      </CardActions>
    </Card>
  );
};

export default AppointmentCard;

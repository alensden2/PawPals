/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck
import { Typography, Divider } from '@mui/material';
import {
  RateReviewOutlined,
  ScheduleOutlined,
  CheckCircleOutlined
} from '@material-ui/icons';

import useStyles from './HorizontalList.styles';
import AppointmentCard from '../AppointmentCard';
import { VET_HOME_APPOINTMENT_CONSTANT } from '@src/constants';
import NOTHING_HERE_YET from '@src/assets/images/nothing_here_yet.png';

const HorizontalList = ({
  appointments,
  status,
  openAppointmentDetailsModal,
  onDeclineAppointmentClick,
  onDiagnoseButtonClick,
  onApproveAppointmentClick
}) => {
  const classes = useStyles();

  const renderIcon = () => {
    switch (status) {
      case 'PENDING': {
        return <RateReviewOutlined className={classes.icon} />;
      }
      case 'CONFIRMED': {
        return <ScheduleOutlined className={classes.icon} />;
      }
      case 'DIAGNOSED': {
        return <CheckCircleOutlined className={classes.icon} />;
      }
    }
  };

  const renderAppointments = () => {
    if (appointments.length === 0) {
      return (
        <div className={classes.emptyStateContainer}>
          <img className={classes.gif} src={NOTHING_HERE_YET} alt="My GIF" />
          <Typography
            variant="h6"
            align="left"
            className={classes.emptyStateText}
          >
            {'Nothing here yet!'}
          </Typography>
        </div>
      );
    }

    return (
      <div className={classes.horizontalListContainer}>
        {appointments.map((appointment) => (
          <div className={classes.cardContainer} key={appointment.id}>
            <AppointmentCard
              appointment={appointment}
              openAppointmentDetailsModal={openAppointmentDetailsModal}
              onApproveAppointmentClick={onApproveAppointmentClick}
              onDeclineAppointmentClick={onDeclineAppointmentClick}
              onDiagnoseButtonClick={onDiagnoseButtonClick}
            />
          </div>
        ))}
      </div>
    );
  };

  return (
    <div className={classes.root}>
      <div className={classes.header}>
        {renderIcon()}
        <Typography variant="h4" align="left" className={classes.listTitle}>
          {VET_HOME_APPOINTMENT_CONSTANT[status].headerText}
        </Typography>
      </div>
      <Divider variant="middle" className={classes.divider} />
      {renderAppointments()}
    </div>
  );
};

export default HorizontalList;

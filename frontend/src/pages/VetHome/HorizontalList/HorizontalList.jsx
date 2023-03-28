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

const HorizontalList = ({
  appointments,
  status,
  openAppointmentDetailsModal,
  onDeclineAppointmentClick,
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

  return (
    <div className={classes.root}>
      <div className={classes.header}>
        {renderIcon()}
        <Typography variant="h4" align="left" className={classes.listTitle}>
          {VET_HOME_APPOINTMENT_CONSTANT[status].headerText}
        </Typography>
      </div>
      <Divider variant="middle" className={classes.divider} />
      <div className={classes.horizontalListContainer}>
        {appointments.map((appointment) => (
          <div className={classes.cardContainer} key={appointment.id}>
            <AppointmentCard
              appointment={appointment}
              openAppointmentDetailsModal={openAppointmentDetailsModal}
              onApproveAppointmentClick={onApproveAppointmentClick}
              onDeclineAppointmentClick={onDeclineAppointmentClick}
            />
          </div>
        ))}
      </div>
    </div>
  );
};

export default HorizontalList;

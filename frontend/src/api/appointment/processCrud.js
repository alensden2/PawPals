// @ts-nocheck

import { bookAppointmentApiCall } from './crud';

export const bookAppointment = async (appointmentDetails) => {
  try {
    return await bookAppointmentApiCall(appointmentDetails);
  } catch (e) {
    console.error(e);
  }
};

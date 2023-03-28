/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck

import {
  registerVetApiCall,
  getAllAppointmentsOfVetApiCall,
  updateStatusOfAppointmentApiCall
} from './crud';

export const registerVet = async (vet) => {
  const response = await registerVetApiCall(vet);
  return response;
};

// TODO: remove vet1 as default param
export const getAllAppointmentsOfVet = async ({ vetUserId = 'vet1' } = {}) => {
  const response = await getAllAppointmentsOfVetApiCall({ vetUserId });

  return response;
};

export const updateStatusOfAppointment = async ({
  appointmentId,
  input
} = {}) => {
  const response = await updateStatusOfAppointmentApiCall({
    appointmentId,
    input
  });

  return response;
};

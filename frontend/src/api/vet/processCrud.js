/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck

import {
  registerVetApiCall,
  getAllAppointmentsOfVetApiCall,
  updateStatusOfAppointmentApiCall,
  getAvailabilityOnSpecificDatApiCall,
  postAvailabilityApiCall
} from './crud';

export const registerVet = async (vet) => {
  const response = await registerVetApiCall(vet);
  return response;
};

export const postAvailability =  async (vetAvailability) => {
  const response = await postAvailabilityApiCall(vetAvailability);
  return response;
}

// TODO: remove vet1 as default param
export const getAllAppointmentsOfVet = async ({ vetUserId = 'vet1' } = {}) => {
  const response = await getAllAppointmentsOfVetApiCall({ vetUserId });

  return response;
};

export const getVetAvailabilityOnSpecificDay = async (
  date = "01-01-2023",
  vetUserId = ""
) =>{
  const response = await getAvailabilityOnSpecificDatApiCall({date, vetUserId});
  return response.data;
}

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

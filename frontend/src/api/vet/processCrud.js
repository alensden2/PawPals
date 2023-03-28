/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck

import {
  registerVetApiCall,
  getAllAppointmentsOfVetApiCall,
  updateStatusOfAppointmentApiCall
} from './crud';
import { getImageUrlFromBytes } from '@src/utils';

export const registerVet = async (vet) => {
  const response = await registerVetApiCall(vet);
  return response;
};

export const getAllAppointmentsOfVet = async ({ vetUserId = 'vet1' } = {}) => {
  const response = await getAllAppointmentsOfVetApiCall({ vetUserId });

  const body = response?.data?.body || [];

  return body.map((item) => {
    return {
      pet: {
        ...item.animalDto,
        photoUrl: item?.animalDto?.photoUrl
          ? getImageUrlFromBytes({ bytes: item.animalDto.photoUrl })
          : ''
      },
      appointment: {
        ...item.appointmentDto
      },
      petOwner: {
        ...item.petOwnerDto,
        photoUrl: item?.petOwner?.photoUrl
          ? getImageUrlFromBytes({ bytes: item.petOwner.photoUrl })
          : ''
      },
      medicalRecord: {
        ...item.medicalHistoryDtos
      }
    };
  });
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

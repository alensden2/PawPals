/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck

import {
  registerVetApiCall,
  getAllAppointmentsOfVetApiCall,
  updateStatusOfAppointmentApiCall,
  getVetByUserIdApiCall,
  getAvailabilityOnSpecificDatApiCall,
  updateProfileStatusVetApiCall,
  getAllPendingVetsApiCall,
  getVetByIdApiCall,
  postAvailabilityApiCall
} from './crud';
import { getImageUrlFromBytes } from '@src/utils';

export const registerVet = async (vet) => {
  const response = await registerVetApiCall(vet);
  return response;
};

export const postAvailability = async (vetAvailability) => {
  const response = await postAvailabilityApiCall(vetAvailability);
  return response;
};

export const getAllAppointmentsOfVet = async () => {
  const response = await getAllAppointmentsOfVetApiCall();

  if (!response?.data?.success) {
    return [];
  }
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
      medicalRecord: [
        ...(item?.medicalHistoryDtos || []).map((item) => {
          return {
            ...item,
            vet: {
              ...item.vet,
              clinicUrl: item?.vet?.clinicUrl
                ? getImageUrlFromBytes({ bytes: item?.vet?.clinicUrl })
                : null
            }
          };
        })
      ]
    };
  });
};

export const getVetAvailabilityOnSpecificDay = async (
  date = '01-01-2023',
  vetUserId = ''
) => {
  const response = await getAvailabilityOnSpecificDatApiCall({
    date,
    vetUserId
  });
  return response.data;
};

export const updateStatusOfAppointment = async ({
  appointmentId,
  input
} = {}) => {
  const response = await updateStatusOfAppointmentApiCall({
    appointmentId,
    input
  });

  return response?.data?.success || false;
};

export const getVetByUserId = async ({ vetUserId } = {}) => {
  const response = await getVetByUserIdApiCall({
    vetUserId
  });

  return response;
};

export const getAllPendingVets = async () => {
  const response = await getAllPendingVetsApiCall();

  const body = response?.data?.body || [];

  return body.map((item) => {
    return {
      ...item,
      clinicUrl: item.clinicUrl
        ? getImageUrlFromBytes({ bytes: item.clinicUrl })
        : ''
    };
  });
};

export const updateProfileStatusVet = async ({ vetUserId, input }) => {
  const response = await updateProfileStatusVetApiCall({ vetUserId, input });

  const isSuccess = response?.data?.success;

  return isSuccess;
};

export const getVetById = async ({ vetUserId }) => {
  const response = await getVetByIdApiCall({ vetUserId });

  const body = response?.data?.body || {};
  return body;
};

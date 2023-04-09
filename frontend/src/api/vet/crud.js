// @ts-nocheck
import { axiosJSON, axiosFORM } from '@src/lib';
import { localStorageUtil } from '@src/utils';

export const registerVetApiCall = async (vet) => {
  let response;
  try {
    const formData = new FormData();
    const formText = { ...vet, clinicPhoto: undefined };
    formData.append(
      'vet',
      new Blob([JSON.stringify(formText)], { type: 'application/json' })
    );
    if (vet.clinicPhoto) {
      formData.append('clinicPhoto', vet.clinicPhoto);
    }

    const responseRaw = await axiosFORM.post('/auth/vet/register', formData);
    response = responseRaw.data;
  } catch (error) {
    console.error(error);
    response = error.errorReponse;
  }
  return response;
};

export const getAllAppointmentsOfVetApiCall = async () => {
  const user = localStorageUtil.getItem('user');
  const vetUserId = user.userName;
  let response;
  try {
    response = await axiosJSON.get(`/auth/vet/appointments/${vetUserId}`);
  } catch (e) {
    response = e.errorReponse;
  }
  return response;
};

export const getAvailabilityOnSpecificDatApiCall = async ({
  date = '01-01-2023',
  vetUserId = ''
}) => {
  return await axiosJSON.post(
    `/auth/vet/availability/${vetUserId}`,
    JSON.stringify({ date })
  );
};

export const postAvailabilityApiCall = async (vetAvailability) => {
  return await axiosJSON.post(`/auth/vet/availability/post`, vetAvailability);
};

export const updateStatusOfAppointmentApiCall = async ({
  appointmentId,
  input
} = {}) => {
  return await axiosJSON.put(`/auth/vet/status/${appointmentId}`, input);
};

export const getVetByUserIdApiCall = async ({ vetUserId } = {}) => {
  return await axiosJSON.get(`/auth/vet/${vetUserId}`);
};

export const getAllPendingVetsApiCall = async () => {
  return await axiosJSON.get(`/auth/vet/pending/vets`);
};

export const updateProfileStatusVetApiCall = async ({ vetUserId, input }) => {
  return await axiosJSON.put(`/auth/vet/profile_status/${vetUserId}`, input);
};

export const getVetByIdApiCall = async ({ vetUserId }) => {
  return await axiosJSON.get(`/auth/vet/${vetUserId}`);
};

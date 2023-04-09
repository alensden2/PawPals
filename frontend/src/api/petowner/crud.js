/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck

import { axiosJSON, axiosFORM } from '@src/lib';
import { localStorageUtil } from '@src/utils';

export const registerPetOwnerApiCall = async (petOwner) => {
  let response;
  try {
    const formData = new FormData();
    const formText = { ...petOwner, photoUrl: undefined };
    formData.append(
      'petowner',
      new Blob([JSON.stringify(formText)], { type: 'application/json' })
    );
    if (petOwner.photoUrl) {
      formData.append('image', petOwner.photoUrl);
    }

    const responseRaw = await axiosFORM.post(
      '/auth/pet-owner/register',
      formData
    );
    response = responseRaw.data;
  } catch (error) {
    console.error(error);
    response = error.errorReponse;
  }
  return response;
};

export const getAllMedicalHistoryOfPetApiCall = async () => {
  const user = localStorageUtil.getItem('user');
  const petOwnerUserId = user.userName;

  try {
    return await axiosJSON.get(
      `/auth/pet-owner/pets/medicalHistory/${petOwnerUserId}`
    );
  } catch (e) {
    console.error(e);
  }
};

export const getAllPetsApiCall = async () => {
  const user = localStorageUtil.getItem('user');
  const petOwnerUserId = user.userName;

  try {
    return await axiosJSON.get(`/auth/pet-owner/pets/${petOwnerUserId}`);
  } catch (e) {
    console.error(e);
  }
};

export const getAllPetOwnerAppointmentsApiCall = async () => {
  const user = localStorageUtil.getItem('user');
  const petOwnerUserId = user.userName;

  try {
    return await axiosJSON.get(
      `/auth/pet-owner/pets/appointments/${petOwnerUserId}`
    );
  } catch (e) {
    console.error(e);
  }
};

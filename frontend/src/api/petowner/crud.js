/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck

import axios from 'axios';

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

    const apiClient = axios.create({
      baseURL: 'http://localhost:8080/'
    });
    const responseRaw = await apiClient.post(
      '/unauth/pet-owner/register',
      formData
    );
    response = responseRaw.data;
  } catch (error) {
    console.error(error);
    response = error.errorReponse;
  }
  return response;
};

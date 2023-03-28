/* eslint-disable @typescript-eslint/ban-ts-comment */
// @ts-nocheck
import axios from 'axios';

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

    const apiClient = axios.create({
      baseURL: 'http://localhost:8080/'
    });
    const responseRaw = await apiClient.post('/unauth/vet/register', formData);
    response = responseRaw.data;
  } catch (error) {
    console.error(error);
    response = error.errorReponse;
  }
  return response;
};

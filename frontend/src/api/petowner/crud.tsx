import { PetOwner } from '@src/types';
import axios, { AxiosResponse, AxiosInstance } from 'axios';
import { ApiResponse } from '../type';

export const registerPetOwnerApiCall = async (
  petOwner: PetOwner
): Promise<ApiResponse> => {
  let response: string;
  try {
    const formData: FormData = new FormData();
    const formText = { ...petOwner, photoUrl: undefined };
    formData.append(
      'petowner',
      new Blob([JSON.stringify(formText)], { type: 'application/json' })
    );
    if (petOwner.photoUrl) {
      formData.append('image', petOwner.photoUrl);
    }

    const apiClient: AxiosInstance = axios.create({
      baseURL: 'http://localhost:8080/'
    });
    const responseRaw = await apiClient.post(
      '/unauth/pet-owner/register',
      formData
    );
    response = responseRaw.data;
  } catch (error: any) {
    console.error(error);
    response = error.errorReponse;
  }
  return response;
};

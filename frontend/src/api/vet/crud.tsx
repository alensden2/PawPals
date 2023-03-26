import axios, { AxiosResponse, AxiosInstance } from 'axios';
import { RegisterVetApiInputType } from './type';

export const registerVetApiCall = async (
  vet: RegisterVetApiInputType
): Promise<string> => {
  let response: string;
  try {
    const formData: FormData = new FormData();
    const formText = { ...vet, clinicPhoto: undefined };
    formData.append(
      'vet',
      new Blob([JSON.stringify(formText)], { type: 'application/json' })
    );
    if (vet.clinicPhoto) {
      formData.append('clinicPhoto', vet.clinicPhoto);
    }

    const apiClient: AxiosInstance = axios.create({
      baseURL: 'http://localhost:8080/'
    });
    const responseRaw = await apiClient.post('/unauth/vet/register', formData);
    response = responseRaw.data;
  } catch (error: any) {
    console.error(error);
    response = error.errorReponse;
  }
  return response;
};

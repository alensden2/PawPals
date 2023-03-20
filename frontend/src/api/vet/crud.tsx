import { axios } from '@src/lib';
import { RegisterVetApiInputType, RegisterVetApiResponseType } from './type';

export const registerVetApiCall = async (
  vet: RegisterVetApiInputType
): Promise<string> => {
  let response: string;
  try {
    const responseRaw = await axios.post('/unauth/vet/register', vet);
    response = responseRaw.data;
  } catch (error: any) {
    console.error(error);
    response = error.errorReponse;
  }
  return response;
};

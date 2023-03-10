import {
  RegisterUserApiInput,
  RegisterUserApiResponse
} from '@src/interfaces/api';
import { axios } from '@src/lib';

export const registerUser = async (
  user: RegisterUserApiInput
): Promise<RegisterUserApiResponse> => {
  let response: RegisterUserApiResponse;

  try {
    response = await axios.post('/unauth/user/register', user);
  } catch (error: any) {
    response = error.errorReponse;
  }

  return response;
};

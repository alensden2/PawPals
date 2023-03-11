import {
  RegisterUserApiInput,
  AuthenticateUserApiInput,
  RegisterUserApiResponse,
  AuthenticateUserApiResponse
} from './type';
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

export const authenticateUser = async (
  user: AuthenticateUserApiInput
): Promise<AuthenticateUserApiResponse> => {
  let response: AuthenticateUserApiResponse;

  try {
    response = await axios.post('/unauth/user/authenticate', user);
  } catch (error: any) {
    response = error.errorReponse;
  }

  return response;
};

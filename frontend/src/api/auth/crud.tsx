import {
  RegisterUserApiInputType,
  AuthenticateUserApiInputType,
  RegisterUserApiResponseType,
  AuthenticateUserApiResponseType
} from './type';
import { axiosJSON as axios } from '@src/lib';

export const registerUserApiCall = async (
  user: RegisterUserApiInputType
): Promise<RegisterUserApiResponseType> => {
  let response: RegisterUserApiResponseType;

  try {
    response = await axios.post('/unauth/user/register', user);
  } catch (error: any) {
    console.error(error);
    response = error.errorReponse;
  }

  return response;
};

export const authenticateUserApiCall = async (
  user: AuthenticateUserApiInputType
): Promise<AuthenticateUserApiResponseType> => {
  let response: AuthenticateUserApiResponseType;

  try {
    response = await axios.post('/unauth/user/authenticate', user);
  } catch (error: any) {
    response = error.errorReponse;
  }

  return response;
};

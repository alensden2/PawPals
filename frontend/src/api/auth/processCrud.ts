import { registerUserApiCall } from './crud';
import {
  RegisterUserApiInputType,
  RegisterUserType,
  RegisterUserApiResponseType
} from './type';

export const registerUser = async (
  user: RegisterUserApiInputType
): Promise<RegisterUserType> => {
  const response: RegisterUserApiResponseType = await registerUserApiCall(user);

  return {
    error: response?.data?.error,
    errorMessage: response.data?.message
  };
};

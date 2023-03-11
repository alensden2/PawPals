import { registerUserApiCall, authenticateUserApiCall } from './crud';
import {
  RegisterUserApiInputType,
  RegisterUserType,
  RegisterUserApiResponseType,
  AuthenticateUserType,
  AuthenticateUserApiResponseType,
  AuthenticateUserApiInputType
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

export const authenticateUser = async (
  user: AuthenticateUserApiInputType
): Promise<AuthenticateUserType> => {
  const response: AuthenticateUserApiResponseType =
    await authenticateUserApiCall(user);

  if (response?.data?.user) {
    return {
      email: response.data.user.email,
      userName: response.data.user.username,
      jwtToken: response.data.token,
      role: response.data.user.authorities[0].authority,
      error: false,
      errorMessage: ''
    };
  } else {
    return {
      email: '',
      userName: '',
      jwtToken: '',
      role: '',
      error: true,
      errorMessage: 'Invalid credentials'
    };
  }
};

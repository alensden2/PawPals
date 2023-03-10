import { AxiosResponse } from 'axios';
import {
  RegisterUserApiInput,
  RegisterUserApiResponse
} from '@src/interfaces/api';
import { axios } from '@src/lib';

export const registerUser = async (
  user: RegisterUserApiInput
): Promise<AxiosResponse<RegisterUserApiResponse>> => {
  console.log('abc');
  const response: AxiosResponse<RegisterUserApiResponse> = await axios.post(
    '/unauth/user/register',
    user
  );

  return response;
};

export type RegisterUserApiInput = {
  userName: string;
  password: string;
  email: string;
  role: string;
};

export type RegisterUserApiResponse = {
  data: {
    error: boolean;
    success: boolean;
    message: string;
    body: string | RegisterUserApiResponseBodyObject;
  };
};

type RegisterUserApiResponseBodyObject = {
  role: string;
  password: string;
  email: string;
  username: string;
};

export type AuthenticateUserApiInput = {
  userName: string;
  password: string;
};

export type AuthenticateUserApiResponse = {
  data: {
    error: boolean;
    success: boolean;
    message: string;
    body: any;
  };
};

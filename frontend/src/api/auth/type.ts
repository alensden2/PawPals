export type RegisterUserApiInputType = {
  userName: string;
  password: string;
  email: string;
  role: string;
};

export type RegisterUserApiResponseType = {
  data: {
    error: boolean;
    success: boolean;
    message: string;
    body: string | RegisterUserApiResponseBodyType;
  };
};

type RegisterUserApiResponseBodyType = {
  role: string;
  password: string;
  email: string;
  username: string;
};

export type AuthenticateUserApiInputType = {
  userName: string;
  password: string;
};

export type AuthenticateUserApiResponseType = {
  data: {
    error: boolean;
    success: boolean;
    message: string;
    body: any;
  };
};

export type RegisterUserType = {
  error: boolean;
  errorMessage: string;
};

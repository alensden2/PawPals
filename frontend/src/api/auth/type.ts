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
  username: string;
  password: string;
};

interface Authority {
  authority: 'VET' | 'PET_OWNER' | 'ROLE_ADMIN';
}

export type AuthenticateUserApiResponseType = {
  data: {
    token: string;
    user: {
      email: string;
      firstName: string;
      lastName: string;
      username: string;
      authorities: Authority[];
    };
  };
};

export type RegisterUserType = {
  error: boolean;
  errorMessage: string;
};

export type AuthenticateUserType = {
  email: string;
  userName: string;
  jwtToken: string;
  role: 'VET' | 'PET_OWNER' | 'ROLE_ADMIN' | '';
  error: boolean;
  errorMessage: string;
};

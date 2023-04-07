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
  authority:
    | 'ROLE_VET'
    | 'ROLE_ADMIN'
    | 'ROLE_PET_OWNER'
    | 'PET_OWNER'
    | 'VET'
    | 'ADMIN';
}

export type AuthenticateUserApiResponseType = {
  data: {
    token: string;
    user: {
      email: string;
      firstName: string;
      lastName: string;
      userName: string;
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
  role:
    | 'ROLE_VET'
    | 'ROLE_ADMIN'
    | 'ROLE_PET_OWNER'
    | 'NONE'
    | 'PET_OWNER'
    | 'VET'
    | 'ADMIN';
  error: boolean;
  errorMessage: string;
};

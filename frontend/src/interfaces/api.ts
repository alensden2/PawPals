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

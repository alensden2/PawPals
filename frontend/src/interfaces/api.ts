export type RegisterUserApiInput = {
  userName: string;
  password: string;
  email: string;
  role: string;
};

export type RegisterUserApiResponse = {
  data: {
    body: {
      error?: boolean;
      success: boolean;
      message: string;
      role: string;
      password: string;
      email: string;
      username: string;
    };
  };
};

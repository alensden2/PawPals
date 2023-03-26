export * from './auth/type';

export interface ApiResponse {
  message?: string;
  error: boolean;
  success: boolean;
  body?: { [key: string]: number | boolean | string | [] | {} };
}

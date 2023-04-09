import axios, { AxiosResponse, AxiosInstance } from 'axios';
import { localStorageUtil } from '@src/utils';

declare const SERVICE_BASE_URL: string;

class API {
  private apiClient: AxiosInstance;

  constructor(contentType: string) {
    this.apiClient = axios.create({
      baseURL: SERVICE_BASE_URL,
      headers: {
        Accept: 'application/json, text/plain', // Set the Accept header to accept JSON and plain text
        // Setting the Accept header to accept application/json and text/plain indicates to the server the types of responses that the client can accept in return.
        'Content-Type': contentType
      }
    });

    this.apiClient.interceptors.request.use(
      (config) => {
        const token = localStorageUtil.getItem('user')?.jwtToken;
        if (token) {
          config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
      },
      (error) => {
        return Promise.reject(error);
      }
    );

    // By adding interceptors to the Axios instance, we can customize the behavior of our API calls globally, without having to modify each function individually
    this.apiClient.interceptors.response.use(
      (response) => response,
      (error) => {
        if (error?.code === 'ERR_NETWORK') {
          localStorageUtil.clear();
          window.location.href = '/';
        }

        if ([400, 403, 406].includes(error.response?.status)) {
          // added response to errorReponse, otherwise axios was not giving response property for rejected promise
          // Issue reference: https://github.com/axios/axios/issues/960
          error.errorReponse = error.response;
          return Promise.reject(error);
        } else {
          return Promise.reject(error);
        }
      }
    );
  }

  public async get(endpoint: string, params?: object): Promise<AxiosResponse> {
    try {
      const response = await this.apiClient.get(`${endpoint}`, {
        params
      });
      return response;
    } catch (error) {
      throw error;
    }
  }

  public async post(endpoint: string, data?: object): Promise<AxiosResponse> {
    try {
      const response = await this.apiClient.post(`${endpoint}`, data);
      return response;
    } catch (error) {
      throw error;
    }
  }

  public async put(endpoint: string, data?: object): Promise<AxiosResponse> {
    try {
      const response = await this.apiClient.put(`${endpoint}`, data);
      return response;
    } catch (error) {
      throw error;
    }
  }

  public async delete(endpoint: string): Promise<AxiosResponse> {
    try {
      const response = await this.apiClient.delete(`${endpoint}`);
      return response;
    } catch (error) {
      throw error;
    }
  }
}

const axiosJSON = new API('application/json');
const axiosFORM = new API('form-data');

export { axiosJSON, axiosFORM };

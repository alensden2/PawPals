import axios, { AxiosResponse } from 'axios';

class API {
  private API_URL: string;

  constructor(url: string) {
    this.API_URL = url;
  }

  public async get(endpoint: string, params?: object): Promise<AxiosResponse> {
    try {
      const response = await axios.get(`${this.API_URL}/${endpoint}`, {
        params
      });
      return response;
    } catch (error) {
      throw error;
    }
  }

  public async post(endpoint: string, data?: object): Promise<AxiosResponse> {
    try {
      const response = await axios.post(`${this.API_URL}/${endpoint}`, data);
      return response;
    } catch (error) {
      throw error;
    }
  }
}

const api = new API('https://api.adviceslip.com');

export default api;

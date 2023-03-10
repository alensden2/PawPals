import axios, { AxiosResponse, AxiosInstance } from 'axios';

class API {
  private apiClient: AxiosInstance;

  constructor() {
    this.apiClient = axios.create({
      baseURL: 'http://localhost:8080/',
      headers: {
        Accept: 'application/json, text/plain', // Set the Accept header to accept JSON and plain text
        // Setting the Accept header to accept application/json and text/plain indicates to the server the types of responses that the client can accept in return.
        'Content-Type': 'application/json' // Set the Content-Type header to JSON
        // Setting the Content-Type header to application/json specifies that the data being sent in the request body is in JSON format.
      }
    });
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
}

const api = new API();

export default api;

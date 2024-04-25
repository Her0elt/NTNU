import { API_URL } from '~/constant';
import axios from 'axios';



import type { ActionResponse } from '~/types';

const axiosInstance = axios.create();



export type HttpRequestOptions = {
  baseUrl?: string;
};

export class HttpRequest {
  url: string;
  withAuth: boolean;
  request: Request;
  apiUrl = API_URL;

  constructor(url: string, request: Request, withAuth = true, options: HttpRequestOptions = {}) {
    this.url = url;
    this.request = request;
    this.withAuth = withAuth;

  }

async #handleRequestError(error: any) {
    if (error.response) {
      if (error.response.status === 401) {
        return Promise.reject({ detail: 'Du har blitt logget ut på grunn av for lang tid med inaktivitet' } as ActionResponse);
      }
      if (typeof error.response.data?.detail === 'string') {
        return Promise.reject({ detail: error.response.data.detail } as ActionResponse);
      }
      return Promise.reject({ detail: `${error.response.status} - ${error.response.statusText}` } as ActionResponse);
    } else if (error.request) {
      return Promise.reject({ detail: 'Forespørselen tok for lang tid å fullføre. Sjekk at internettilkoblingen din virker.' } as ActionResponse);
    } else if (error.message) {
      return Promise.reject({ detail: error.message } as ActionResponse);
    }
    return Promise.reject({ detail: 'Noe gikk aldeles galt' } as ActionResponse);
  }
  async get<ReturnType>() {
      return axiosInstance
          .get<ReturnType>(`${this.apiUrl}/${this.url}`)
          .then((data) => data.data)
          .catch(this.#handleRequestError);}

    async post<ReturnType>(data?: any) {
        return axiosInstance
          .post<ReturnType>(`${this.apiUrl}/${this.url}`, data, {})
          .then((data) => data.data)
          .catch(this.#handleRequestError);
  }
  async put<ReturnType>(data?: unknown) {
     return axiosInstance
          .put<ReturnType>(`${this.apiUrl}/${this.url}`, data)
          .then((data) => data.data)
          .catch(this.#handleRequestError);  }

  async delete<ReturnType>() {
      return axiosInstance
          .delete<ReturnType>(`${this.apiUrl}/${this.url}`)
          .then((data) => data.data)
          .catch(this.#handleRequestError);}

}

const API_BASE_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080/api';

export interface LoginRequest {
  username: string;
  password: string;
}

export interface LoginResponse {
  token: string;
  type: string;
  username: string;
  roles: string[];
}

export interface RegisterRequest {
  username: string;
  email: string;
  password: string;
  roles: string[];
}

class ApiClient {
  private token: string | null = null;

  constructor() {
    this.token = localStorage.getItem('auth_token');
  }

  setToken(token: string) {
    this.token = token;
    localStorage.setItem('auth_token', token);
  }

  clearToken() {
    this.token = null;
    localStorage.removeItem('auth_token');
  }

  private async request<T>(
    method: string,
    endpoint: string,
    body?: unknown
  ): Promise<T> {
    const headers: HeadersInit = {
      'Content-Type': 'application/json'
    };

    if (this.token) {
      headers.Authorization = `Bearer ${this.token}`;
    }

    const options: RequestInit = {
      method,
      headers
    };

    if (body) {
      options.body = JSON.stringify(body);
    }

    const response = await fetch(`${API_BASE_URL}${endpoint}`, options);

    // Handle no-content responses
    if (response.status === 204 || response.status === 201) {
      if (response.ok) {
        const contentType = response.headers.get('content-type');
        if (contentType && contentType.includes('application/json')) {
          return response.json();
        }
        return {} as T;
      }
    }

    // Handle error responses
    if (!response.ok) {
      const contentType = response.headers.get('content-type');
      let errorMessage = response.statusText;
      
      if (contentType && contentType.includes('application/json')) {
        try {
          const error = await response.json();
          errorMessage = error.message || error.error || response.statusText;
        } catch {
          errorMessage = response.statusText;
        }
      }
      
      throw new Error(errorMessage || `API error: ${response.status}`);
    }

    // Handle successful responses with body
    const contentType = response.headers.get('content-type');
    if (contentType && contentType.includes('application/json')) {
      return response.json();
    }

    return {} as T;
  }

  async login(credentials: LoginRequest): Promise<LoginResponse> {
    return this.request<LoginResponse>('POST', '/auth/login', credentials);
  }

  async register(data: RegisterRequest): Promise<void> {
    return this.request<void>('POST', '/auth/register', data);
  }

  async getPatients() {
    return this.request('GET', '/patients');
  }

  async getDoctors() {
    return this.request('GET', '/doctors');
  }

  async getAppointments() {
    return this.request('GET', '/appointments');
  }
}

export const apiClient = new ApiClient();

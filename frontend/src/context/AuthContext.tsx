import React, { createContext, useContext, useMemo, useState } from 'react';
import { apiClient, LoginResponse } from '../services/apiClient';

export interface AuthCredentials {
  username: string;
  password: string;
}

export interface AuthContextValue {
  authenticated: boolean;
  user: LoginResponse | null;
  signIn: (credentials: AuthCredentials) => Promise<void>;
  signOut: () => void;
  loading: boolean;
  error: string | null;
}

const AuthContext = createContext<AuthContextValue | null>(null);

export const AuthProvider = ({ children }: { children: React.ReactNode }) => {
  const [authenticated, setAuthenticated] = useState(false);
  const [user, setUser] = useState<LoginResponse | null>(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const signIn = async ({ username, password }: AuthCredentials) => {
    setLoading(true);
    setError(null);
    try {
      const response = await apiClient.login({ username, password });
      apiClient.setToken(response.token);
      setUser(response);
      setAuthenticated(true);
    } catch (err) {
      const message = err instanceof Error ? err.message : 'Login failed';
      setError(message);
      throw err;
    } finally {
      setLoading(false);
    }
  };

  const signOut = () => {
    apiClient.clearToken();
    setAuthenticated(false);
    setUser(null);
    setError(null);
  };

  const value = useMemo(
    () => ({ authenticated, user, signIn, signOut, loading, error }),
    [authenticated, user, loading, error]
  );

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
};

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error('useAuth must be used within AuthProvider');
  }
  return context;
};

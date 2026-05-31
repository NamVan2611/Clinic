import React, { createContext, useContext, useMemo, useState } from 'react';

export interface AuthCredentials {
  email: string;
  password: string;
  remember: boolean;
}

export interface AuthContextValue {
  authenticated: boolean;
  userEmail: string;
  signIn: (credentials: AuthCredentials) => Promise<void>;
  signOut: () => void;
}

const AuthContext = createContext<AuthContextValue | null>(null);

export const AuthProvider = ({ children }: { children: React.ReactNode }) => {
  const [authenticated, setAuthenticated] = useState(false);
  const [userEmail, setUserEmail] = useState('');

  const signIn = async ({ email, password, remember }: AuthCredentials) => {
    return new Promise<void>((resolve) => {
      window.setTimeout(() => {
        setAuthenticated(true);
        setUserEmail(email);
        if (remember) {
          localStorage.setItem('mediflow-auth', email);
        }
        resolve();
      }, 900);
    });
  };

  const signOut = () => {
    setAuthenticated(false);
    setUserEmail('');
    localStorage.removeItem('mediflow-auth');
  };

  const value = useMemo(
    () => ({ authenticated, userEmail, signIn, signOut }),
    [authenticated, userEmail]
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

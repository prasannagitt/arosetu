import { createContext, useContext, useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import api from '../api/axios';

const AuthContext = createContext(null);

export function AuthProvider({ children }) {
  const [user, setUser] = useState(null);
  const [token, setToken] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const savedToken = localStorage.getItem('token');
    const savedUser = localStorage.getItem('user');
    if (savedToken && savedUser) {
      setToken(savedToken);
      setUser(JSON.parse(savedUser));
    }
    setLoading(false);
  }, []);

  const login = async (email, password) => {
    const res = await api.post('/auth/login', { email, password });
    const data = res.data;
    localStorage.setItem('token', data.token);
    localStorage.setItem('user', JSON.stringify({
      userId: data.userId, name: data.name, email: data.email, role: data.role
    }));
    setToken(data.token);
    setUser({ userId: data.userId, name: data.name, email: data.email, role: data.role });
    return data;
  };

  const register = async (formData) => {
    const res = await api.post('/auth/register', formData);
    const data = res.data;
    localStorage.setItem('token', data.token);
    localStorage.setItem('user', JSON.stringify({
      userId: data.userId, name: data.name, email: data.email, role: data.role
    }));
    setToken(data.token);
    setUser({ userId: data.userId, name: data.name, email: data.email, role: data.role });
    return data;
  };

  const logout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    setToken(null);
    setUser(null);
  };

  const value = {
    user,
    token,
    loading,
    isAuthenticated: !!token,
    login,
    register,
    logout,
  };

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}

export function useAuth() {
  const ctx = useContext(AuthContext);
  if (!ctx) throw new Error('useAuth must be used within AuthProvider');
  return ctx;
}


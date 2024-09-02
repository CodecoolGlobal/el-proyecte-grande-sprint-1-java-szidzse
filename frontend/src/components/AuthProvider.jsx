import React, { createContext, useState, useContext } from 'react';

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [userRoles, setUserRoles] = useState([]);

  const login = async (email, password) => {
    try {
      const response = await fetch('/api/member/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ email, password }),
      });
  
      if (!response.ok) {
        const errorText = await response.text();
        throw new Error(`Login failed: ${errorText}`);
      }
  
      const data = await response.json();
      sessionStorage.setItem('accessToken', data.jwt);
      setUserRoles(data.roles);
      
      return data;
    } catch (error) {
      console.error('Login error:', error);
      return false;
    }
  };

  const logout = () => {
    sessionStorage.removeItem('accessToken');
    setUserRoles(null);
  };

  return (
    <AuthContext.Provider value={{ userRoles, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);

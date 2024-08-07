import React from 'react'
import { useNavigate } from 'react-router-dom';
import LoginForm from '../components/LoginForm';
import { useAuth } from "../components/AuthProvider";

const loginMember = async (memberEmailPassword) => {
    try {
        const response = await fetch('/api/member/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(memberEmailPassword),
        });

        const result = await response.json();
        return result;

    } catch (error) {
        console.error('Error:', error);
        return false;
    }
};

export const Login = () => {
    const navigate = useNavigate();
    const { login } = useAuth();

    const handleSignUp = () => {
        navigate('/signup')
    }

    const handleLogin = async (memberEmailPassword) => {
        const success = await loginMember(memberEmailPassword);
        if (success) {
          login();
          navigate('/');
          console.log('Login successful');
        } else {
          console.log('Login failed');
        }
      };

  return (
    <LoginForm
    onSignup={handleSignUp}
    loginMember={handleLogin}
    />
  )
}

export default Login;

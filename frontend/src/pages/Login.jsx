import React from 'react'
import { useNavigate } from 'react-router-dom';
import LoginForm from '../components/LoginForm';

// TODO Under Implemention!! //

// const loginMember = async(memberEmailPassword) => {
//     const res = await fetch('/api/member')

// }

export const Login = () => {
    const navigate = useNavigate();

    const handleSignUp = () => {
        navigate('/signup')
    }

    const handleLogin = (memberEmailPassword) => {
        navigate('/')
    }

  return (
    <LoginForm
    onSignup={handleSignUp}
    loginMember={handleLogin}
    />
  )
}

export default Login;

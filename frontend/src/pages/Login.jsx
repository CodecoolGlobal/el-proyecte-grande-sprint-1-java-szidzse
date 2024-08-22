import React, { useState } from 'react';
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
        console.log('API response:', result);
        const { access_token, refresh_token } = result.jwt;       
        localStorage.setItem("accessToken", access_token);
        localStorage.setItem("refreshToken", refresh_token);
        return result;
        

    } catch (error) {
        console.error('Error:', error);
        return false;
    }
};

export const Login = () => {
    const navigate = useNavigate();
    const { login } = useAuth();
    const [isModalOpen, setModalOpen] = useState(false);

    const handleSignUp = () => {
        navigate('/signup');
    }

    const handleLogin = async (memberEmailPassword) => {
        const success = await loginMember(memberEmailPassword);
        if (success) {
          login();
          navigate('/');
          console.log('Login successful');
        } else {
          console.log('Login failed');
          setModalOpen(true); // Show the modal if login fails
        }
    };

    const closeModal = () => {
        setModalOpen(false);
    }

  return (
    <>
    <LoginForm
      onSignup={handleSignUp}
      loginMember={handleLogin}
    />
    {isModalOpen && (
        <div className="fixed z-10 inset-0 overflow-y-auto" id="my-modal">
            <div className="flex items-end justify-center min-h-screen pt-4 px-4 pb-20 text-center sm:block sm:p-0">
                <div className="fixed inset-0 transition-opacity" aria-hidden="true">
                    <div className="absolute inset-0 bg-gray-500 opacity-75"></div>
                </div>
                <span className="hidden sm:inline-block sm:align-middle sm:h-screen" aria-hidden="true">&#8203;</span>
                <div className="inline-block align-bottom bg-white rounded-lg px-4 pt-5 pb-4 text-left overflow-hidden shadow-xl transform transition-all sm:my-8 sm:align-middle sm:max-w-lg sm:w-full"
                    role="dialog" aria-modal="true" aria-labelledby="modal-headline">
                    <div>
                        <div className="mx-auto flex items-center justify-center h-12 w-12 rounded-full bg-red-100">
                            <svg className="h-6 w-6 text-red-600" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
                                stroke="currentColor" aria-hidden="true">
                                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2"
                                    d="M6 18L18 6M6 6l12 12" />
                            </svg>
                        </div>
                        <div className="mt-3 text-center sm:mt-5">
                            <h3 className="text-lg leading-6 font-medium text-gray-900" id="modal-headline">
                            Invalid email or password
                            </h3>
                            <div className="mt-2">
                                <p className="text-sm text-gray-500">
                                Unable to log in: Incorrect email or password
                                </p>
                            </div>
                        </div>
                    </div>
                    <div className="mt-5 sm:mt-6">
                        <button
                            className="inline-flex justify-center w-full rounded-md border border-transparent shadow-sm px-4 py-2 bg-red-600 text-base font-medium text-white hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-red-500 sm:text-sm"
                            onClick={closeModal}>
                            OK
                        </button>
                    </div>
                </div>
            </div>
        </div>
    )}
    </>
  );
}

export default Login;

import React from "react";
import LoginForm from "../components/LoginForm";

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
        sessionStorage.setItem("accessToken", access_token);
        sessionStorage.setItem("refreshToken", refresh_token);
        return result;


    } catch (error) {
        console.error('Error:', error);
        return false;
    }
};

const LoginPage = () => {
    return <LoginForm />;
};

export default LoginPage;

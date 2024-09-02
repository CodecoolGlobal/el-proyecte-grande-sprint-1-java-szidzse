import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { RegisterForm } from "../components/RegisterForm";

const createMember = async (member) => {
    const res = await fetch("/api/member/register", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(member),
    });
};

const RegisterPage = () => {
    const [loading, setLoading] = useState(true);
    const navigate = useNavigate();

    const handleCreateMember = async (member) => {
        await createMember(member);
        setLoading(false);
        navigate("/");
    };

    const handleLogin = () => {
        navigate("/login");
    };

    return (
        <RegisterForm onSave={handleCreateMember} onLogin={handleLogin} />
    );
};

export default RegisterPage;
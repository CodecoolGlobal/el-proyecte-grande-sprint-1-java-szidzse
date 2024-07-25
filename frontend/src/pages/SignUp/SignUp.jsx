import React, {useState} from 'react';
import SignUpForm from '/src/components/form/SignupForm.jsx';
import {useNavigate} from "react-router-dom";

const createUser = async (user) => {
    try {
        const userBody = {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(user),
        };

        const response = await fetch('/api/member', userBody)

        if (!response.ok) {
            throw new Error('Network response was not ok');
        }

        const data = await response.json();
        return data;

    } catch (error) {
        console.error("There was a problem with the fetch operation:", error);
        throw error;
    }
};

export default function SignUp() {
    const navigate = useNavigate();
    const [loading, setLoading] = useState(false);

    const handleCreateUser = async (user) => {
        setLoading(true);

        try {
            await createUser(user);
            setLoading(false);
            navigate("/");
        } catch (error) {
            setLoading(false);
            console.error("Error creating user:", error);
        }
    }

    return (
            <SignUpForm
            onSave={handleCreateUser}
            disabled={loading}
            />
    );
};
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import SignUpForm from "../components/SignUpForm";

const createMember = async (member) => {
  const res = await fetch("/api/member/register", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(member),
  });
};

const SignUp = () => {
    const [loading, setLoading] = useState(true);
    const navigate = useNavigate();

    const handleCreateMember = async (member) => {
        await createMember(member);
        setLoading(false);
        navigate("/");
    }

    const handleLogin = () => {
      navigate("/login");
    }

  return <SignUpForm 
  onSave={handleCreateMember} 
  onLogin = {handleLogin}
  />;
};

export default SignUp;

import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import SignUpForm from "../components/SignUpForm";

const createMember = async (member) => {
  const res = await fetch("/api/member", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(member),
  });
  const data = await res.json();
  return data;
};

const SignUp = () => {
    const [loading, setLoading] = useState(true);
    const navigate = useNavigate();

    const handleCreateMember = async (member) => {
        const fetchData = await createMember(member);
        setLoading(false);
        navigate("/");
        
    }

  return <SignUpForm onSave={handleCreateMember} />;
};

export default SignUp;

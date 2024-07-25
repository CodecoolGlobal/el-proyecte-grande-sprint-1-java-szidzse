import React, {useEffect, useState} from 'react'
import './LoginSignup.css'

import emailIcon from '../../assets/email.png'
import passwordIcon from '../../assets/password.png'
import personIcon from '../../assets/person.png'

const createUser = async (user) => {
    try {
        const userBody = {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(user),
        };

        const response = await fetch('/api/members', userBody)

        if (!response.ok) {
            throw new Error('Network response was not ok');
        }

        const data = await response.json();
        return data;

    } catch (error) {
        console.error("There was a problem with the fetch operation:", error);
    }
};

const LoginSignup = () => {
    const [action, setAction] = useState("Login")
    const [user, setUser] = useState(null);

    useEffect(() => {
    createUser();
    }, [])

    return (
        <div className='container'>
            <div className='header'>
                <div className='text'> {action}</div>
                <div className='underline'></div>
            </div>
            <div className='inputs'>
                <div className='input'>
                    <img src={personIcon} alt=""/>
                    <input type='text' placeholder='Name'/>
                </div>
                <div className='input'>
                    <img src={emailIcon} alt=""/>
                    <input type='email' placeholder='Email'/>
                </div>
                <div className='input'>
                    <img src={[passwordIcon]} alt=""/>
                    <input type='password' placeholder='Password'/>
                </div>
                <div className="forgot-password"> Lost password? <span>Click here</span></div>
                <div className="submit-container"></div>
                <div className={action === "Login" ? "submit gray" : 'submit'} onClick={() => setAction("Sign Up")}>
                    Sign Up
                </div>
                <div className={action === "Sign Up" ? "submit gray" : 'submit'} onClick={() => setAction("Sign Up")}>
                    Login
                </div>
            </div>
        </div>
    )
}
export default LoginSignup

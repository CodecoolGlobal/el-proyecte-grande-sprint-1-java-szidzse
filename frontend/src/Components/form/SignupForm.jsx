import {useEffect, useState} from "react";
import './SubmitButton.css'
import emailIcon from '../../assets/email.png'
import passwordIcon from '../../assets/password.png'
import personIcon from '../../assets/person.png'


export default function Form({onSave, disabled}) {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');


    const onSubmit = (e) => {
        e.preventDefault();

        return onSave({first_name: firstName, last_name: lastName, email, password});

    }

    return (<div className='container'>
            <div className='header'>
                <div className='text'>Sign Up</div>
                <div className='underline'></div>
            </div>
            <form className='form' onSubmit={onSubmit}>
                <div className='inputs'>
                    <div className='input'>
                        <img src={personIcon} alt="Person Icon" className='icon'/>
                        <input
                            type='text'
                            placeholder='First Name'
                            value={firstName}
                            onChange={(e) => setFirstName(e.target.value)}
                            disabled={disabled}
                        />
                    </div>
                    <div className='input'>
                        <img src={personIcon} alt="Person Icon" className='icon'/>
                        <input
                            type='text'
                            placeholder='Last Name'
                            value={lastName}
                            onChange={(e) => setLastName(e.target.value)}
                            disabled={disabled}
                        />
                    </div>
                    <div className='input'>
                        <img src={emailIcon} alt="Email Icon" className='icon'/>
                        <input
                            type='email'
                            placeholder='Email'
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            disabled={disabled}
                        />
                    </div>
                    <div className='input'>
                        <img src={passwordIcon} alt="Password Icon" className='icon'/>
                        <input
                            type='password'
                            placeholder='Password'
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            disabled={disabled}
                        />
                    </div>
                </div>
                <div className="buttons">
                    <button
                        className="button-40"
                        type="submit"
                        disabled={disabled}
                    >
                        Submit
                    </button>
                </div>
            </form>
        </div>
    );
}
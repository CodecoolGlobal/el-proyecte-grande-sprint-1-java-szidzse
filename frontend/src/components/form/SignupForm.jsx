import { useEffect, useState } from "react";
import { v4 as uuidv4 } from 'uuid';  // Importáljuk az UUID generátort
import './SubmitButton.css'
import emailIcon from '../../assets/email.png'
import passwordIcon from '../../assets/password.png'
import personIcon from '../../assets/person.png'
import './FormSignUp.css'

export default function Form({ onSave, disabled }) {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const onSubmit = (e) => {
        e.preventDefault();
        const newId = uuidv4();

        onSave({ member_id: newId,first_name: firstName, last_name: lastName, email, password });
    }

    return (
        <div className='form-container'>
            <div className='form-header'>
                <div className='text'>Sign Up</div>
                <div className='underline'></div>
            </div>
            <form className='form' onSubmit={onSubmit}>
                <div className='form-inputs'>
                    <div className='form-input'>
                        <img src={personIcon} alt="Person Icon" className='icon' />
                        <input
                            type='text'
                            placeholder='First Name'
                            value={firstName}
                            onChange={(e) => setFirstName(e.target.value)}
                            disabled={disabled}
                        />
                    </div>
                    <div className='form-input'>
                        <img src={personIcon} alt="Person Icon" className='icon' />
                        <input
                            type='text'
                            placeholder='Last Name'
                            value={lastName}
                            onChange={(e) => setLastName(e.target.value)}
                            disabled={disabled}
                        />
                    </div>
                    <div className='form-input'>
                        <img src={emailIcon} alt="Email Icon" className='icon' />
                        <input
                            type='email'
                            placeholder='Email'
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            disabled={disabled}
                        />
                    </div>
                    <div className='form-input'>
                        <img src={passwordIcon} alt="Password Icon" className='icon' />
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

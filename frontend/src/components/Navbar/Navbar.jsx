import React, {useState} from 'react';
import {Link, Outlet} from 'react-router-dom';
//import {Button} from 'react';
import './Navbar.css'
//import Dropdown from './Dropdown';

import {navItems} from './NavbarItems/NavItems.jsx'

export default function Navbar() {
    const [click, setClick] = useState(false);

    const handleClick = () => setClick(!click);

    return (
        <>
            <nav className="navbar">
                <Link to="/" className="navbar-logo">
                    RESTMates
                </Link>
                <ul className="nav-items">
                    {navItems.map(item => {
                        return (
                            <li key={item.id} className={item.cName}>
                                <Link to={item.path}>
                                    {item.title}
                                </Link>
                            </li>
                        )
                    })}
                </ul>
                <div className="menu-icon" onClick={handleClick}>

                </div>
            </nav>
        </>
    );
}
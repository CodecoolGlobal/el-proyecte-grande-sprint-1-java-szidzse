import React from 'react';
import { Link } from 'react-router-dom';

const NavbarMenu = ({ isLoggedIn, handleClick, handleLogout }) => {
    
  return (
    <ul className="menu menu-sm dropdown-content bg-base-100 rounded-box z-10 mt-3 w-52 p-2 shadow-lg absolute right-0">
      <li onClick={handleClick}>
        <Link to="/">Home</Link>
      </li>
      {isLoggedIn ? (
        <>
          <li onClick={handleClick}>
            <a className="justify-between">Profile</a>
          </li>
          <li onClick={handleClick}>
            <Link to="/settings">Settings</Link>
          </li>
          <li onClick={handleClick}>
            <a onClick={handleLogout}>Logout</a>
          </li>
        </>
      ) : (
        <>
          <li onClick={handleClick}>
            <Link to="/signup">Sign Up</Link>
          </li>
          <li onClick={handleClick}>
            <Link to="/login">Log In</Link>
          </li>
        </>
      )}
    </ul>
  );
};

export default NavbarMenu;

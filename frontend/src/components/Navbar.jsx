import React, { useState, useEffect } from "react";
import { Link, Outlet } from "react-router-dom";

const Navbar = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [openNavigation, setOpenNavigation] = useState(false);

  const toggleNavigation = () => {
    setOpenNavigation((prev) => !prev);
  };

  const handleClick = () => {
    setOpenNavigation(false);
  };

  useEffect(() => {
    
  }, []);

  return (
    <>
      <div className="navbar bg-base-100">
        <div className="flex-1">
          <Link to="/" className="flex items-center">
            <img
              src="/src/assets/restmates.svg"
              alt="RESTMates Logo"
              className="w-16 h-16 mr-2"
            />
            <span className="text-xl normal-case">RESTMates</span>
          </Link>
        </div>
        <div className="flex-none">
          <div className="relative">
            <div
              tabIndex={0}
              role="button"
              className="btn btn-ghost btn-circle avatar"
              onClick={toggleNavigation}
            >
              <div className="w-10 rounded-full">
                <img
                  alt="User Avatar"
                  src="https://img.daisyui.com/images/stock/photo-1534528741775-53994a69daeb.webp"
                />
              </div>
            </div>

            {openNavigation && (
              <ul className="menu menu-sm dropdown-content bg-base-100 rounded-box z-[1] mt-3 w-52 p-2 shadow absolute right-0">
                <li onClick={handleClick}>
                  <Link to="/">Home</Link>
                </li>
                {isLoggedIn ? (
                  <>
                    <li onClick={handleClick}>
                      <a className="justify-between">
                        Profile
                        <span className="badge">New</span>
                      </a>
                    </li>
                    <li onClick={handleClick}>
                      <a>Settings</a>
                    </li>
                    <li onClick={handleClick}>
                      <a>Logout</a>
                    </li>
                  </>
                ) : (
                  <>
                    <li onClick={handleClick}>
                      <Link to="/signUp">Sign Up</Link>
                    </li>
                    <li onClick={handleClick}>
                      <a>Login</a>
                    </li>
                    <li onClick={handleClick}>
                      <a>Settings</a>
                    </li>
                  </>
                )}
              </ul>
            )}
          </div>
        </div>
      </div>
      <Outlet />
    </>
  );
};

export default Navbar;

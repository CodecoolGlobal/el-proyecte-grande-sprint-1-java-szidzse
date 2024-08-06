import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";

const Navbar = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(true);

  // Példa bejelentkezési állapot ellenőrzés (helyettesítsd a valós logikával)
  useEffect(() => {
    // Itt ellenőrizheted a bejelentkezési állapotot pl. egy API hívással
    // setIsLoggedIn(true); // Ha be van jelentkezve
    // setIsLoggedIn(false); // Ha nincs bejelentkezve
  }, []);

  return (
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
        <div className="dropdown dropdown-end">
          <div
            tabIndex={0}
            role="button"
            className="btn btn-ghost btn-circle avatar"
          >
            <div className="w-10 rounded-full">
              <img
                alt="User Avatar"
                src="https://img.daisyui.com/images/stock/photo-1534528741775-53994a69daeb.webp"
              />
            </div>
          </div>
          <ul
            tabIndex={0}
            className="menu menu-sm dropdown-content bg-base-100 rounded-box z-[1] mt-3 w-52 p-2 shadow"
          >
            {isLoggedIn ? (
              <li>
                <a>Logout</a>
              </li>
            ) : (
              <>
                <li>
                  <a className="justify-between">
                    Profile
                    <span className="badge">New</span>
                  </a>
                </li>
                <li>
                  <a>Sign Up</a>
                </li>
                <li>
                  <a>Login</a>
                </li>
              </>
            )}
            <li>
              <a>Settings</a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  );
};

export default Navbar;

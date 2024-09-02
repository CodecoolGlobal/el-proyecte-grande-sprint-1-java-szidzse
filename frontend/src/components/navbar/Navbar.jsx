import React, { useState } from "react";
import { Outlet, useNavigate } from "react-router-dom";
import { useAuth } from "../AuthProvider";

import NavbarMenu from "./NavbarMenu";
import Logo from "./Logo";
import Footer from "./Footer.jsx";
import NavbarMenuDisplayOpener from "./NavbarMenuDisplayOpener";

const Navbar = () => {
  const [openNavbarMenu, setOpenNavbarMenu] = useState(false);
  const navigate = useNavigate();
  const { isLoggedIn, logout } = useAuth();

  const handleOpenNavbarMenu = () => {
    setOpenNavbarMenu((prev) => !prev);
  };

  const handleClick = () => {
    setOpenNavbarMenu(false);
  };

  const handleLogout = () => {
    logout();
    navigate("/");
  };

  return (
    <>
      <div className="navbar bg-base-100">
        <Logo />
        <div className="flex-none">
          <div className="relative">
            <NavbarMenuDisplayOpener
              handleOpenNavbarMenu={handleOpenNavbarMenu}
            />
            {openNavbarMenu && (
              <NavbarMenu
                isLoggedIn={isLoggedIn}
                handleClick={handleClick}
                handleLogout={handleLogout}
              />
            )}
          </div>
        </div>
      </div>
      <Outlet />
      <Footer />
    </>
  );
};

export default Navbar;

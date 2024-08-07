import React, { useState } from "react";
import { Link, Outlet, useNavigate } from "react-router-dom";
import NavbarMenu from "./NavbarMenu";
import Logo from "./Logo";
import NavbarMenuDisplayOpener from "./NavbarMenuDisplayOpener";

const Navbar = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [openNavbarMenu, setOpenNavbarMenu] = useState(false);
  const navigate = useNavigate();

  const handleOpenNavbarMenu = () => {
    setOpenNavbarMenu((prev) => !prev);
  };

  const handleClick = () => {
    setOpenNavbarMenu(false);
  };

  const handleLogout = () => {
    setIsLoggedIn(false);
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
    </>
  );
};

export default Navbar;

import React from "react";

export const NavbarMenuDisplayOpener = ({ handleOpenNavbarMenu }) => {
  return (
      <div
        tabIndex={0}
        role="button"
        className="btn btn-ghost btn-circle avatar"
        onClick={handleOpenNavbarMenu}
      >
        <div className="w-10 rounded-full">
          <img
            alt="User Avatar"
            src="/src/assets/avatar-loading-svgrepo-com.svg"
          />
        </div>
      </div>
  );
};

export default NavbarMenuDisplayOpener;

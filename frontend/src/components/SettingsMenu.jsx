import React from "react";
import { Link } from "react-router-dom";

export const SettingsMenu = () => {
  return (
    <ul className="menu bg-base-200 rounded-box w-56">
      <li>
        <a>
            <img 
            alt="Edit Own Accommodations"
            src="/src/assets/home-svgrepo-com.svg"
            className="h-5 w-5 object-contain"
            />
          Edit Own Accommodations
        </a>
      </li>
      <li >
        <a >
          <img
            alt="Edit Profil"
            src="/src/assets/edit-rectangle-svgrepo-com.svg"
            className="h-5 w-5 object-contain"
          />
          <Link to="/settings/editprofile/1"/>
        </a>
      </li>
      <li>
        <a>
          <img
            alt="Delete Account"
            src="/src/assets/delete-profile-svgrepo-com.svg"
            className="h-5 w-5 object-contain"
          />
          <span>Delete Account</span>
        </a>
      </li>
    </ul>
  );
};

export default SettingsMenu;

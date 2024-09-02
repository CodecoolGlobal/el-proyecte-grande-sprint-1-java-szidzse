import React from "react";
import { Outlet, useNavigate } from "react-router-dom";

import SettingsMenu from "../../components/navbar/SettingsMenu.jsx";


export const SettingsPage = () => {
  return (
    <div className="flex">
      <aside className="w-1/4 bg-gray-200 p-4">
        <SettingsMenu />
      </aside>
      <main className="w-3/4 p-4">
        <Outlet />
      </main>
    </div>
  );
};

export default SettingsPage;

import { createBrowserRouter } from "react-router-dom";
import Dashboard from "./pages/Dashboard.jsx";
import { Contact } from "./pages/Contact.jsx";
import React from "react";
import Navbar from "./components/navbar/Navbar.jsx";
import LandingPage from "./pages/LandingPage.jsx";
import SignUp from "./pages/SignUp.jsx";
import Login from "./pages/Login.jsx";
import ProfiEdit from "./pages/settingsPage/ProfiEdit.jsx";
import SettingsPage from "./pages/settingsPage/Settings.jsx";

export const router = createBrowserRouter([
  {
    path: "/",
    element: <Navbar />,
    // errorElement: <ErrorPage/>,
    children: [
      {
        path: "/",
        element: <LandingPage />,
      },
      {
        path: "/dashboard",
        element: <Dashboard />,
      },
      {
        path: "/contact",
        element: <Contact />,
      },
      {
        path: "/signup",
        element: <SignUp />,
      },
      {
        path: "/login",
        element: <Login />,
      },
      {
        path: "/settings",
        element: <SettingsPage />,
        children: [
          {
            path: "/settings/editprofile/:id",
            element: <ProfiEdit />
        }
      ],
      },
    ],
  },
]);

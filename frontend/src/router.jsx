import {createBrowserRouter} from "react-router-dom";
import Dashboard from "./pages/Dashboard.jsx";
import {Contact} from "./pages/Contact.jsx";
import React from "react";
import Navbar from "./components/Navbar.jsx";
import LandingPage from "./pages/LandingPage.jsx";
import SignUp from "./pages/SignUp.jsx";

export const router = createBrowserRouter([
    {
        path: "/",
        element: <Navbar/>,
        // errorElement: <ErrorPage/>,
        children: [
            {
                path: "/",
                element: <LandingPage/>
            },
            {
                path: "/dashboard",
                element: <Dashboard/>
            },
            {
                path: "/contact",
                element: <Contact/>
            },
            {
                path: "/signup",
                element: <SignUp/>
            }
        ]
    }
])
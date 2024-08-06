import {createBrowserRouter} from "react-router-dom";
import App from "./App.jsx";
import Dashboard from "./routes/Dashboard.jsx";
import {Contact} from "./routes/Contact.jsx";
import React from "react";

export const router = createBrowserRouter([
    {path: "/", element: <App/>},
    {path: "/dashboard", element: <Dashboard/>},
    {path: "/contact", element: <Contact/>}
])
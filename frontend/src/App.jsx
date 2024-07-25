import React, { useEffect, useState } from 'react';
import { createBrowserRouter, RouterProvider, Outlet } from 'react-router-dom';
import MainPage from './pages/Main/MainPage.jsx';
import AccommodationsPage from "./pages/Accommodations/AccommodationsPage.jsx"
// import ErrorPage from './pages/ErrorPage/ErrorPage.jsx';
import Navbar from "./components/Navbar/Navbar.jsx";
import './index.css';

const AppLayout = () => {
    const currentTheme = localStorage.getItem("currentTheme");
    const [theme, setTheme] = useState(currentTheme ? currentTheme : "light");

    useEffect(() => {
        localStorage.setItem("currentTheme", theme);
    }, [theme]);

    return (
        <div className={`container ${theme}`}>
            <Navbar theme={theme} setTheme={setTheme} />
            <Outlet />
        </div>
    );
};

const router = createBrowserRouter([
    {
        path: '/',
        element: <AppLayout />,
        // errorElement: <ErrorPage />,
        children: [
            {
                path: '/',
                element: <MainPage />,
            },
            {
                path: "/accommodations",
                element: <AccommodationsPage />
            }
        ],
    },
]);

const App = () => (
    <RouterProvider router={router} />
);

export default App;
import React from 'react';
import ReactDOM from 'react-dom/client';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import reportWebVitals from "./reportWebVitals.js";
import MainPage from './pages/Main/MainPage.jsx';
import Navbar from './components/Navbar/Navbar.jsx';
//import ErrorPage from './pages/ErrorPage/ErrorPage.jsx';

const router = createBrowserRouter([
    {
        path: '/',
        element: <Navbar />,
        // errorElement: <ErrorPage />,
        children: [
            {
                path: '/',
                element: <MainPage />,
            },
        ],
    },
]);

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <React.StrictMode>
        <RouterProvider router={router} />
    </React.StrictMode>
);

reportWebVitals();
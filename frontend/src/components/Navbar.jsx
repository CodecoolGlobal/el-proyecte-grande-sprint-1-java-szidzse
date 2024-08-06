import React from 'react'
import {Link, Outlet} from "react-router-dom";

const Navbar = () => {
    return (
        <div>
            <h1>Navbar</h1>
            <p>
                <Link to="/">Home</Link>
            </p>
            <p>
                <Link to="/dashboard">Dashboard</Link>
            </p>
            <p>
                <Link to="/contact">Contact</Link>
            </p>
            <Outlet/>
        </div>
    )
}
export default Navbar

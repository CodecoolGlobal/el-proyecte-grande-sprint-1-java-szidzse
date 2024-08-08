import React from "react";

const Footer = () => (
  <footer className="footer footer-center bg-base-100 text-base-content p-4 md:fixed md:bottom-0 md:w-full shadow-inner ">
    <aside>
      <p>
        © RESTMates {new Date().getFullYear()} - All rights reserved by
        RESTMates Industries Ltd
      </p>
    </aside>
  </footer>
);

export default Footer;

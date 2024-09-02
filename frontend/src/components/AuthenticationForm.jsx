import React from "react";
import { useState } from "react";

export const AuthenticationForm = ({ onSignup, loginMember }) => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const onSubmit = (e) => {
    e.preventDefault();

    return loginMember({
      email,
      password,
    });
  };

  return (
    <div className="flex justify-center items-center w-full min-h-screen bg-cover bg-center bg-no-repeat bg-[url('/src/assets/signup-bg.png')] px-3 py-3">
      <div className="w-full shadow-md max-w-md mx-auto bg-white bg-opacity-70 backdrop-blur-md drop-shadow-xl border border-black/20 rounded-md flex flex-col items-center px-3 py-3 animate-fade-up animate-once animate-duration-1000">
        <div className="hidden md:flex sm:w-[60%] lg:w-[50%] bg-cover bg-center items-center justify-center">
          <img
            src="src/assets/user-profile-person-svgrepo-com.svg"
            alt="login"
            className="h-[150px]"
          />
        </div>
        <div className="w-full lg:w-1/2 py-3">
          <h1 className="text-center text-2xl sm:text-3xl font-semibold text-[#4A07DA]">
            Log In
          </h1>
          <div className="w-full mt-5 sm:mt-8">
            <div className="mx-auto w-full flex flex-col gap-3">
              <input
                type="email"
                placeholder="Email"
                className="input input-bordered input-primary w-full text-black placeholder:text-black/70"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
              />
              <input
                type="password"
                placeholder="Password"
                className="input input-bordered input-primary w-full text-black placeholder:text-black/70"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
              <div className="flex items-center gap-1.5 justify-start pl-2">
                <div className="form-control">
                  <label className="label cursor-pointer">
                    <input
                      type="checkbox"
                      className="checkbox-xs checkbox-primary"
                    />
                  </label>
                </div>
                <h3 className="flex items-center whitespace-nowrap text-xs text-black">
                  I agree to the
                  <span className="text-[#4A07DA]">&nbsp;Terms</span>
                  &nbsp;and
                  <span className="text-[#4A07DA]">&nbsp;Privacy Policy</span>.
                </h3>
              </div>
              <div className="flex flex-col md:flex-row gap-2 md:gap-4 justify-center items-center">
                <button
                  className="btn btn-active btn-primary btn-block max-w-[150px]"
                  onClick={onSignup}
                >
                  Sign Up
                </button>
                <button
                  className="btn btn-outline btn-primary btn-block max-w-[150px]"
                  onClick={onSubmit}
                >
                  Log In
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AuthenticationForm;

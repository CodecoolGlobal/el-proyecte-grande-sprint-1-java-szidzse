/** @type {import('tailwindcss').Config} */
import daisyui from "daisyui";

export default {
    content: ["./index.html", "./src/**/*.{js,ts,jsx,tsx}"],
    theme: {
        extend: {
            colors: {
                customGreen: '#bef264',
                customBlue: '#22d3ee',
            },
        },
    },
    plugins: [
        require('tailwindcss-animated'),
        daisyui,
    ],
    daisyui: {
        themes: ["autumn", "business"],
    },
    darkMode: 'class',
};

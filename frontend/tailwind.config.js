/** @type {import('tailwindcss').Config} */
import daisyui from "daisyui";
import withMT from "@material-tailwind/react/utils/withMT";

export default withMT({
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
        themes: ["valentine"],
    },
    darkMode: 'class',
});

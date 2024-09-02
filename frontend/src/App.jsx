import { Route, Routes } from "react-router-dom";
import { NavigationBar } from "./components/NavigationBar";
import HomePage from "./pages/HomePage";
import RegisterPage from "./pages/RegisterPage";
import LoginPage from "./pages/LoginPage";
import Footer from "./components/navbar/Footer.jsx";

const App = () => {
    return (
        <>
            <NavigationBar />
            <Routes>
                <Route path="/" element={<HomePage />}></Route>
                <Route path="/register" element={<RegisterPage />}></Route>
                <Route path="/login" element={<LoginPage />}></Route>
            </Routes>
            <Footer />
        </>
    );
};

export default App;

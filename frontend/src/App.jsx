import { Route, Routes } from "react-router-dom";
import { NavigationBar } from "./components/navigationBar/NavigationBar.jsx";
import HomePage from "./pages/HomePage";
import RegisterPage from "./pages/RegisterPage";
import LoginPage from "./pages/LoginPage";
import Footer from './components/navigationBar/Footer.jsx'
import ProfilViewPage from './pages/ProfilViewPage.jsx'

const App = () => {
    return (
        <>
            <NavigationBar />
            <Routes>
                <Route path="/" element={<HomePage />}></Route>
                <Route path="/register" element={<RegisterPage />}></Route>
                <Route path="/login" element={<LoginPage />}></Route>
                <Route path="/profile" element={<ProfilViewPage />}></Route>
            </Routes>
            <Footer />
        </>
    );
};

export default App;

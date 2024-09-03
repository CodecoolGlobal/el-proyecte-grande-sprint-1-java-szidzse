import { Route, Routes } from "react-router-dom";
import { NavigationBar } from "./components/navigationBar/NavigationBar.jsx";
import HomePage from "./pages/HomePage";
import RegisterPage from "./pages/RegisterPage";
import LoginPage from "./pages/LoginPage";
import Footer from "./components/navigationBar/Footer.jsx";
import ProfileViewPage from "./pages/ProfileViewPage.jsx";
import ProfileEditPage from "./pages/ProfileEditPage.jsx";
import AccommodationDetails from "./components/AccommodationDetails.jsx";
import CountrySearchInput from "./components/CountrySearchInput.jsx";

const App = () => {
  return (
    <div className="flex flex-col min-h-screen">
      <NavigationBar />
      <main className="flex-grow">
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/register" element={<RegisterPage />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/accommodation/:accommodationId" element={<AccommodationDetails />}></Route>
          <Route path="/profile" element={<ProfileViewPage />} />
          <Route path="/profileEdit" element={<ProfileEditPage />} />
          <Route path="/search" element={<CountrySearchInput />} />
        </Routes>
      </main>
      <Footer />
    </div>
  );
};

export default App;

import React, { useEffect, useState } from "react";
import SearchAccommodationInput from "../components/accommodations/SearchAccommodationInput.jsx";
import AccommodationListDisplay from "../components/accommodations/AccommodationListDisplay.jsx";

const HomePage = () => {
  const [accommodations, setAccommodations] = useState([]);
  const [isSearching, setIsSearching] = useState(false);
  const [searchResults, setSearchResults] = useState([]);

  useEffect(() => {
    const fetchAccommodations = async () => {
      try {
        const response = await fetch("/api/accommodation/all");
        if (!response.ok) {
          throw new Error("Failed to fetch accommodations.");
        }
        const data = await response.json();
        setAccommodations(data);
      } catch (error) {
        console.error("Error fetching accommodations:", error);
      }
    };
    if (!isSearching) {
      fetchAccommodations();
    }
  }, [isSearching]);

  return (
    <div className="container mx-auto px-4 py-8">
      <div className="flex justify-center mb-8">
        <SearchAccommodationInput
          className="w-full max-w-md"
          setSearchResults={setSearchResults}
          setIsSearching={setIsSearching}
        />
      </div>
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
        <AccommodationListDisplay
          accommodationList={isSearching ? searchResults : accommodations}
        />
      </div>
    </div>
  );
};

export default HomePage;

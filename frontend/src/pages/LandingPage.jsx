import React, { useEffect, useState } from "react";
import { getAllAccommodations } from "../controllers/accommodationsController";
import LoadingButton from "../components/LoadingState";

const fetchAccommodations = async () => {
    const data = await getAllAccommodations();
    return await data;
};

const LandingPage = () => {
  const [accommodations, setAccommodations] = useState([]);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const data = await fetchAccommodations();
        setAccommodations(data);
      } catch (error) {
        setError(error.message);
      } finally {
        setLoading(false);
      }
    };
    fetchData();
  }, []);

  return (
    <div>
      <h1>Accommodations</h1>
      {loading && <LoadingButton />}
      {error && <p>Error: {error}</p>}
      <ul>
        {accommodations.map((accommodation) => (
          <li key={accommodation.id}>
            <h2>{accommodation.name}</h2>
            <p>{accommodation.description}</p>
            <p>Price: {accommodation.pricePerNight}</p>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default LandingPage;

import React, {useEffect, useState} from "react";
import {getAllAccommodations} from "../controllers/accommodationsController";

const LandingPage = () => {
    const [accommodations, setAccommodations] = useState([]);
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(true);

    const fetchAccommodations = async () => {
        try {
            const data = await getAllAccommodations();
            setAccommodations(data);
        } catch (error) {
            setError(error.message);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchAccommodations();
    }, []);

    return (
        <div>
            <h1>Accommodations</h1>
            {loading && <p>Loading...</p>}
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

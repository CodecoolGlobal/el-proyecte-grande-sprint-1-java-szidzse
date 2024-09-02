import React, {useEffect, useState} from "react";
import {getAllAccommodations} from "../controllers/accommodationsController";
import LoadingButton from "../components/LoadingState";
import AccommodationCard from "../components/AccommodationCard.jsx";
import FilterModal from "../components/FilterModal.jsx";

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
        
        <div className="p-4">
            <h1 className="text-2xl font-bold mb-4">Accommodations:</h1>
            <FilterModal/>
            {loading && <LoadingButton/>}
            {error && <p>Error: {error}</p>}
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-4">
                {accommodations.map((accommodation) => (
                    <AccommodationCard
                        key={accommodation.id}
                        id={accommodation.id}
                        name={accommodation.name}
                        description={accommodation.description}
                        roomNumber={accommodation.roomNumber}
                        pricePerNight={accommodation.pricePerNight}
                        maxGuests={accommodation.maxGuests}
                        location={accommodation.location}
                    />
                ))}
            </div>
        </div>
    );
};

export default LandingPage;

import React, {useEffect, useState} from 'react'
import {useParams} from "react-router-dom";
import {getAccommodationById} from "../controllers/accommodationsController.js";
import LoadingButton from "../components/LoadingState.jsx";
import AccommodationCardDetails from "../components/AccommodationCardDetails.jsx";

const AccommodationDetails = () => {
    const {id} = useParams();
    const [accommodation, setAccommodation] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const data = await getAccommodationById(id);
                setAccommodation(data);
            } catch (error) {
                setError(error.message);
            } finally {
                setLoading(false);
            }
        };
        fetchData();
    }, [id]);

    if (loading) {
        return <LoadingButton/>;
    }

    if (error) {
        return <p>Error: {error}</p>;
    }

    return (
        <AccommodationCardDetails
            id={accommodation.id}
            name={accommodation.name}
            description={accommodation.description}
            roomNumber={accommodation.roomNumber}
            pricePerNight={accommodation.pricePerNight}
            maxGuests={accommodation.maxGuests}
        />
    );
}
export default AccommodationDetails

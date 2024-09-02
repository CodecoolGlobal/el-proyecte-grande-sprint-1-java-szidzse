import React, {useEffect, useState} from 'react'
import AccommodationCard from "../components/AccommodationCard.jsx";

const HomePage = () => {
    const [accommodations, setAccommodations] = useState([])

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
        }
        fetchAccommodations();
    }, []);

    return (
        <div className="container mx-auto px-4 py-8 grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
            {accommodations.map((accommodation) => (
                <AccommodationCard
                    key={accommodation.id}
                    accommodationId={accommodation.id}
                    name={accommodation.name}
                    description={accommodation.description}
                    pricePerNight={accommodation.pricePerNight}
                    location={accommodation.location}
                />
            ))}
        </div>
    )
}

export default HomePage
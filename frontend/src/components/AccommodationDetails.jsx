// src/components/AccommodationDetails.jsx
import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom"; // To access route parameters
import { Card, CardBody, Typography, Button } from "@material-tailwind/react";
import { GalleryWithCarousel } from "./GalleryWithCarousel.jsx";

export default function AccommodationDetails() {
    const { accommodationId } = useParams();
    const [accommodation, setAccommodation] = useState(null);

    useEffect(() => {
        const fetchAccommodationDetails = async () => {
            try {
                const response = await fetch(`/api/accommodation/${accommodationId}`);
                if (!response.ok) {
                    throw new Error("Failed to fetch accommodation details.");
                }
                const data = await response.json();
                console.log(data)
                setAccommodation(data);
            } catch (error) {
                console.error("Error fetching accommodation details:", error);
            }
        };

        fetchAccommodationDetails();
    }, [accommodationId]);

    if (!accommodation) {
        return <div>Loading...</div>;
    }

    return (
        <Card className="max-w-3xl mx-auto my-8 shadow-cardHover rounded-4xl">
            <div className="w-full h-64 rounded-t-4xl overflow-hidden">
                <GalleryWithCarousel accommodationId={accommodation.id} />
            </div>

            <CardBody className="p-6">
                <Typography variant="h4" className="text-primary font-semibold mb-4">
                    {accommodation.name}
                </Typography>
                <Typography className="text-gray-500 mb-4">
                    {accommodation.location.city}, {accommodation.location.state ? accommodation.location.state + ", " : ""} {accommodation.location.country}
                </Typography>
                <Typography className="text-gray-700 mb-4">{accommodation.description}</Typography>
                <Typography variant="h6" className="text-accent">
                    {accommodation.pricePerNight} $ / night
                </Typography>
            </CardBody>
            <div className="p-6">
                <Button size="lg" className="bg-primary text-white hover:bg-secondary">
                    Book Now
                </Button>
            </div>
        </Card>
    );
}

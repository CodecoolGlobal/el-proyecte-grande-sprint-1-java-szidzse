import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { Card, CardBody, Typography, Button } from "@material-tailwind/react";
import {FeaturedImageGallery} from "./FeauturedImageGallery.jsx";

export default function AccommodationDetails() {
    const { accommodationId } = useParams();
    const [accommodation, setAccommodation] = useState(null);
    const [images, setImages] = useState([]);

    useEffect(() => {
        const fetchAccommodationDetails = async () => {
            try {
                const response = await fetch(`/api/accommodation/${accommodationId}`);
                if (!response.ok) {
                    throw new Error("Failed to fetch accommodation details.");
                }
                const data = await response.json();
                setAccommodation(data);
            } catch (error) {
                console.error("Error fetching accommodation details:", error);
            }
        };

        const fetchAccommodationImages = async () => {
            try {
                const response = await fetch(`/api/accommodation/${accommodationId}/images`);
                if (!response.ok) {
                    throw new Error("Failed to fetch accommodation images.");
                }
                const data = await response.json();
                setImages(data);
            } catch (error) {
                console.error("Error fetching accommodation images:", error);
            }
        };

        fetchAccommodationDetails();
        fetchAccommodationImages();
    }, [accommodationId]);

    if (!accommodation) {
        return <div>Loading...</div>;
    }

    return (
        <FeaturedImageGallery images={images} />
    );
}

import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { FeaturedImageGallery } from "./FeauturedImageGallery.jsx";
import { Card, CardBody, Typography, Button } from "@material-tailwind/react";

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
		<div className="container mx-auto px-4 py-6">
			<div className="grid grid-cols-1 md:grid-cols-2 gap-6 mb-8">
				<div className="bg-gray-100 rounded-lg shadow-lg p-4">
					<FeaturedImageGallery images={images} />
				</div>

				{/* Google Maps Placeholder */}
				<div className="bg-gray-100 rounded-lg shadow-lg p-4">
					<div className="w-full h-[300px] bg-gray-200 rounded-lg">
						{/* Placeholder for Google Maps */}
						<p className="text-center text-gray-500">Google Maps will be here.</p>
					</div>
				</div>
			</div>
		</div>
	);
}

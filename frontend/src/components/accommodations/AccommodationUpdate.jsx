import { useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { Button, Input } from "@material-tailwind/react";

export default function AccommodationUpdate() {
	const { accommodationId } = useParams();
	const navigate = useNavigate();
	const [formData, setFormData] = useState({
		name: "",
		description: "",
		roomNumber: 0,
		pricePerNight: 0.0,
		maxGuests: 0,
		locationId: 0,
	});
	const token = sessionStorage.getItem("accessToken");

	const handleChange = (e) => {
		setFormData({ ...formData, [e.target.name]: e.target.value });
	};

	const handleSubmit = async (e) => {
		e.preventDefault();

		try {
			const response = await fetch(`/api/accommodation/${accommodationId}`, {
				method: "PUT",
				headers: {
					"Content-Type": "application/json",
					Authorization: `Bearer ${token}`,
				},
				body: JSON.stringify(formData),
			});

			if (!response.ok) {
				throw new Error("Failed to update accommodation.");
			}

            console.log(response.status);
            

			console.log("Accommodation updated successfully.");
			navigate("/accommodations-manager");
		} catch (error) {
			console.error("Error updating accommodation: ", error);
		}
	};

	return (
		<form onSubmit={handleSubmit} className="flex flex-col gap-4 p-4">
			<Input label="Name" name="name" value={formData.name} onChange={handleChange} />
			<Input
				label="Description"
				name="description"
				value={formData.description}
				onChange={handleChange}
			/>
			<Input
				label="Room Number"
				name="roomNumber"
				type="number"
				value={formData.roomNumber}
				onChange={handleChange}
			/>
			<Input
				label="Price Per Night"
				name="pricePerNight"
				type="number"
				step="0.01"
				value={formData.pricePerNight}
				onChange={handleChange}
			/>
			<Input
				label="Max Guests"
				name="maxGuests"
				type="number"
				value={formData.maxGuests}
				onChange={handleChange}
			/>
			<Input
				label="Location ID"
				name="locationId"
				type="number"
				value={formData.locationId}
				onChange={handleChange}
			/>
			<Button type="submit" color="green">
				Update Accommodation
			</Button>
		</form>
	);
}

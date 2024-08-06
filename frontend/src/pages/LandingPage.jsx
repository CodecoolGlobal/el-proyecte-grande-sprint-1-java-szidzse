import React from "react";
import { useState, useEffect } from "react";
import { getAllAccommodations } from "../controllers/accommodationsController";

const LandingPage = () => {
	const [accommodations, setAccommodations] = useState([]);
	const [error, setError] = useState(null);

	const fetchAccommodations = async () => {
		try {
			const data = await getAllAccommodations();
			setAccommodations(data);
		} catch (error) {
			setError(error.message);
		}
	};

	useEffect(() => {
		fetchAccommodations();
	}, []);

	return (
		<div>
			<h1>Accommodations</h1>
			{error && <p>Error: {error}</p>}
			<ul>
				{accommodations.map((accommodation) => (
					<li key={accommodation.id}>
						<h2>{accommodation.name}</h2>
						<p>{accommodation.description}</p>
						<p>Price: {accommodation.price}</p>
					</li>
				))}
			</ul>
		</div>
	);
};

export default LandingPage;

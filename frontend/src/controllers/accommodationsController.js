// Get all accommodations

const getAllAccommodations = async () => {
	const response = await fetch("/api/accommodation/all");
	const data = await response.json();

	if (!response.ok) {
		throw Error(data.error);
	}

	return data;
};

// Get all accommodations
export const getAllAccommodations = async () => {
    const response = await fetch("/api/accommodation/all");
    const data = await response.json();

    if (!response.ok) {
        throw Error(data.error);
    }

    return data;
};

// Get accommodation by id
export const getAccommodationById = async (id) => {
    const response = await fetch(`/api/accommodation/${id}`);
    const data = await response.json();

    if (!response.ok) {
        throw Error(data.error);
    }

    return data;
}
package com.codecool.restmates.model.dto.responses;

public record AccommodationDTO(String name, String description, Integer roomNumber, Double pricePerNight, Integer maxGuests, LocationCityAndCountryDTO location) {
}

package com.codecool.restmates.model.dto.responses;

public record AccommodationDTO(Long id, String name, String description, Integer roomNumber, Double pricePerNight, Integer maxGuests, LocationCityStateCountryDTO location) {
}

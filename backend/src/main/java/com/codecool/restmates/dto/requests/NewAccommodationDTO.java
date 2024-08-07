package com.codecool.restmates.dto.requests;

public record NewAccommodationDTO(String name, String description, Integer roomNumber, Double pricePerNight, Integer maxGuests, Long ownerId, Long locationId) {
}

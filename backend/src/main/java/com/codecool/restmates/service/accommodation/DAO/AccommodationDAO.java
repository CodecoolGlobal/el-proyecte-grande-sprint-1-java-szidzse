package com.codecool.restmates.service.accommodation.DAO;

import com.codecool.restmates.model.Accommodation;
import com.codecool.restmates.service.accommodation.DTO.AccommodationResponseDTO;
import com.codecool.restmates.service.accommodation.DTO.NewAccommodationDTO;

import java.util.List;

public interface AccommodationDAO {
    Accommodation findAccommodationById(long accommodationId);

    AccommodationResponseDTO createAccommodation(NewAccommodationDTO accommodation);

    List<AccommodationResponseDTO> findAllAccommodation();

    boolean deleteAccommodationById(long accommodationId);
}

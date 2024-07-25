package com.codecool.restmates.repositories.AccommodationDAO;

import com.codecool.restmates.dto.responses.AccommodationResponseDTO;
import com.codecool.restmates.dto.requests.NewAccommodationDTO;

import java.util.List;

public interface AccommodationDAO {
    AccommodationResponseDTO findAccommodationById(long accommodationId);

    AccommodationResponseDTO createAccommodation(NewAccommodationDTO accommodation);

    List<AccommodationResponseDTO> findAllAccommodation();

    boolean deleteAccommodationById(long accommodationId);
}

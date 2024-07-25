package com.codecool.restmates.service;

import com.codecool.restmates.repositories.AccommodationDAO.AccommodationDAO;
import com.codecool.restmates.dto.responses.AccommodationResponseDTO;
import com.codecool.restmates.dto.requests.NewAccommodationDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccommodationService {
    private AccommodationDAO accommodationDAO;

    public AccommodationService(AccommodationDAO accommodationDAO) {
        this.accommodationDAO = accommodationDAO;
    }

    public AccommodationResponseDTO findAccommodationById(long accommodationId) {
        return accommodationDAO.findAccommodationById(accommodationId);
    }

    public AccommodationResponseDTO createAccommodation(NewAccommodationDTO accommodation) {
        return accommodationDAO.createAccommodation(accommodation);
    }

    public List<AccommodationResponseDTO> findAllAccommodation() {
        return accommodationDAO.findAllAccommodation();
    }

    public boolean deleteAccommodationById(long accommodationId) {
        return accommodationDAO.deleteAccommodationById(accommodationId);
    }
}

package com.codecool.restmates.service.accommodation;

import com.codecool.restmates.model.Accommodation;
import com.codecool.restmates.service.accommodation.DAO.AccommodationDAO;
import com.codecool.restmates.service.accommodation.DTO.AccommodationResponseDTO;
import com.codecool.restmates.service.accommodation.DTO.NewAccommodationDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccommodationService {
    private AccommodationDAO accommodationDAO;

    public AccommodationService(AccommodationDAO accommodationDAO) {
        this.accommodationDAO = accommodationDAO;
    }

    public Accommodation findAccommodationById(long accommodationId) {
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

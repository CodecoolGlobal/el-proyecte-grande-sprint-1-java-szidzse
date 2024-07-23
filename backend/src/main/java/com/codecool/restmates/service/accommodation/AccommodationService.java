package com.codecool.restmates.service.accommodation;

import com.codecool.restmates.model.Accommodation;
import com.codecool.restmates.service.accommodation.DAO.AccommodationDAO;
import org.springframework.stereotype.Service;

@Service
public class AccommodationService {
    private AccommodationDAO accommodationDAO;

    public AccommodationService(AccommodationDAO accommodationDAO) {
        this.accommodationDAO = accommodationDAO;
    }

    public Accommodation findAccommodationById(long accommodationId) {
        return accommodationDAO.findAccommodationById(accommodationId);
    }
}

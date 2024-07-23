package com.codecool.restmates.service.accommodation.DAO;

import com.codecool.restmates.model.Accommodation;

public interface AccommodationDAO {
    Accommodation findAccommodationById(long accommodationId);
}

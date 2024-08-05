package com.codecool.restmates.service;

import com.codecool.restmates.exception.ResourceNotFoundException;
import com.codecool.restmates.model.Location;
import com.codecool.restmates.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {
    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Optional<Location> getLocationById(Long locationId) {
        return locationRepository.findById(locationId);
    }

    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    public Location updateLocation(Long locationId, Location updatedLocation) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found with id: " + locationId));

        location.setStreet(updatedLocation.getStreet());
        location.setCity(updatedLocation.getCity());
        location.setState(updatedLocation.getState());
        location.setCountry(updatedLocation.getCountry());
        location.setZipCode(updatedLocation.getZipCode());

        return locationRepository.save(location);
    }

    public boolean deleteLocation(Long locationId) {
        if (locationRepository.existsById(locationId)) {
            locationRepository.deleteById(locationId);
            return true;
        } else {
            throw new ResourceNotFoundException("Location not found with id: " + locationId);
        }
    }
}

package com.codecool.restmates.service;

import com.codecool.restmates.dto.requests.NewLocationDTO;
import com.codecool.restmates.dto.responses.LocationDTO;
import com.codecool.restmates.exception.ResourceNotFoundException;
import com.codecool.restmates.model.Location;
import com.codecool.restmates.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocationService {
    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public LocationDTO getLocationById(Long locationId) {
        Optional<Location> location = locationRepository.findById(locationId);

        if (location.isPresent()) {
            Location locationEntity = location.get();

            return convertToDTO(locationEntity);
        } else {
            throw new ResourceNotFoundException(String.format("Location with id %s not found!", locationId));
        }
    }

    public Long createLocation(NewLocationDTO newLocation) {
        Location location = new Location();

        location.setStreet(newLocation.street());
        location.setCity(newLocation.city());
        location.setState(newLocation.state());
        location.setCountry(newLocation.country());
        location.setZipCode(newLocation.zipCode());

        locationRepository.save(location);

        return location.getId();
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

    private LocationDTO convertToDTO(Location location) {
        return new LocationDTO(
                location.getStreet(),
                location.getCity(),
                location.getState(),
                location.getCountry(),
                location.getZipCode()
        );
    }
}

package com.codecool.restmates.controller;

import com.codecool.restmates.exception.ResourceNotFoundException;
import com.codecool.restmates.model.Location;
import com.codecool.restmates.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/location")
public class LocationController {
    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Location>> getAllLocations() {
        List<Location> locations = locationService.getAllLocations();
        return ResponseEntity.ok(locations);
    }

    @GetMapping(path = "/{locationId}")
    public ResponseEntity<Location> getLocationById(@PathVariable Long locationId) {
        Optional<Location> location = locationService.getLocationById(locationId);

        return location.map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found with id: " + locationId));
    }

    @PostMapping(path = "")
    public ResponseEntity<Location> createLocation(@RequestBody Location location) {
        Location createdLocation = locationService.createLocation(location);
        return ResponseEntity.ok(createdLocation);
    }

    @PutMapping(path = "/{locationId}")
    public ResponseEntity<Location> updateLocation(
            @PathVariable Long locationId,
            @RequestBody Location location) {
        Location updatedLocation = locationService.updateLocation(locationId, location);
        return ResponseEntity.ok(updatedLocation);
    }

    @DeleteMapping(path = "/{locationId}")
    public ResponseEntity<Boolean> deleteLocation(@PathVariable Long locationId) {
        boolean isDeleted = locationService.deleteLocation(locationId);
        return ResponseEntity.ok(isDeleted);
    }
}

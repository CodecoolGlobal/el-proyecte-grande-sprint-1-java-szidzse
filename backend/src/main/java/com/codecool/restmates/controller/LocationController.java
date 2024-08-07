package com.codecool.restmates.controller;

import com.codecool.restmates.dto.requests.NewLocationDTO;
import com.codecool.restmates.dto.responses.LocationDTO;
import com.codecool.restmates.model.Location;
import com.codecool.restmates.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/location")
public class LocationController {
    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping(path = "/{locationId}")
    public LocationDTO getLocationById(@PathVariable Long locationId) {
        return locationService.getLocationById(locationId);
    }

    @PostMapping(path = "")
    public Long createLocation(@RequestBody NewLocationDTO newLocation) {
        return locationService.createLocation(newLocation);
    }

    @PutMapping(path = "/{locationId}")
    public Long updateLocation(@PathVariable Long locationId, @RequestBody NewLocationDTO newLocation) {
        return locationService.updateLocation(locationId, newLocation);
    }

    @DeleteMapping(path = "/{locationId}")
    public ResponseEntity<Boolean> deleteLocation(@PathVariable Long locationId) {
        boolean isDeleted = locationService.deleteLocation(locationId);
        return ResponseEntity.ok(isDeleted);
    }
}

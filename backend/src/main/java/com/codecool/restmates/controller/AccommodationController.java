package com.codecool.restmates.controller;

import com.codecool.restmates.model.Accommodation;
import com.codecool.restmates.service.AccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/accommodation")
public class AccommodationController {
    private final AccommodationService accommodationService;

    @Autowired
    public AccommodationController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @GetMapping(path = "/all")
    public List<Accommodation> getAllAccommodations() {
        return accommodationService.getAllAccommodations();
    }

    @GetMapping(path = "/{accommodationId}")
    public ResponseEntity<Accommodation> getAccommodationById(@PathVariable Long accommodationId) {
        Optional<Accommodation> accommodation = accommodationService.getAccommodationById(accommodationId);

        if (accommodation.isPresent()) {
            return ResponseEntity.ok(accommodation.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = "")
    public ResponseEntity<Accommodation> createAccommodation(
            @RequestBody Accommodation accommodation,
            @RequestParam Long ownerId,
            @RequestParam Long locationId) {
        Accommodation createdAccommodation = accommodationService.createAccommodation(accommodation, ownerId, locationId);
        return ResponseEntity.ok(createdAccommodation);
    }

    @PutMapping(path = "/{accommodationId}")
    public ResponseEntity<Accommodation> updateAccommodation(
            @PathVariable Long accommodationId,
            @RequestBody Accommodation accommodation) {
        Accommodation updatedAccommodation = accommodationService.updateAccommodation(accommodationId, accommodation);
        return ResponseEntity.ok(updatedAccommodation);
    }

    @DeleteMapping(path = "/{accommodationId}")
    public ResponseEntity<Boolean> deleteAccommodation(@PathVariable Long accommodationId) {
        boolean isDeleted = accommodationService.deleteAccommodation(accommodationId);
        return ResponseEntity.ok(isDeleted);
    }
}

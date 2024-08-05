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

    @GetMapping(path = "/{id}")
    public ResponseEntity<Accommodation> getAccommodationById(@PathVariable Long id) {
        Optional<Accommodation> accommodation = accommodationService.getAccommodationById(id);

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

    @PutMapping(path = "/{id}")
    public ResponseEntity<Accommodation> updateAccommodation(
            Long accommodationId,
            Accommodation accommodation) {
        Accommodation updatedAccommodation = accommodationService.updateAccommodation(accommodationId, accommodation);
        return ResponseEntity.ok(updatedAccommodation);
    }
}

package com.codecool.restmates.controller;

import com.codecool.restmates.dto.requests.NewAccommodationDTO;
import com.codecool.restmates.dto.responses.AccommodationDTO;
import com.codecool.restmates.model.Accommodation;
import com.codecool.restmates.service.AccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/accommodation")
public class AccommodationController {
    private final AccommodationService accommodationService;

    @Autowired
    public AccommodationController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @GetMapping(path = "/all")
    public List<AccommodationDTO> getAllAccommodations() {
        return accommodationService.getAllAccommodations();
    }

    @GetMapping(path = "/{accommodationId}")
    public AccommodationDTO getAccommodationById(@PathVariable Long accommodationId) {
        return accommodationService.getAccommodationById(accommodationId);
    }

    @PostMapping(path = "")
    public Long createAccommodation(@RequestBody NewAccommodationDTO newAccommodation) {
        return accommodationService.createAccommodation(newAccommodation);
    }

    @PutMapping(path = "/{accommodationId}")
    public Long updateAccommodation(@PathVariable Long accommodationId, @RequestBody NewAccommodationDTO newAccommodation) {
        return accommodationService.updateAccommodation(accommodationId, newAccommodation);
    }

    @DeleteMapping(path = "/{accommodationId}")
    public ResponseEntity<Boolean> deleteAccommodation(@PathVariable Long accommodationId) {
        boolean isDeleted = accommodationService.deleteAccommodation(accommodationId);
        return ResponseEntity.ok(isDeleted);
    }
}

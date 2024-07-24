package com.codecool.restmates.controller;

import com.codecool.restmates.model.Accommodation;
import com.codecool.restmates.service.accommodation.AccommodationService;
import com.codecool.restmates.service.accommodation.DTO.AccommodationResponseDTO;
import com.codecool.restmates.service.accommodation.DTO.NewAccommodationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class AccommodationController {
    private final AccommodationService accommodationService;

    @Autowired
    public AccommodationController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @GetMapping(path = "/accommodation/{accommodationId}")
    public Accommodation getAccommodation(@PathVariable long accommodationId) {
        return accommodationService.findAccommodationById(accommodationId);
    }

    @PostMapping(path = "/accommodation")
    public AccommodationResponseDTO createAccommodation(@RequestBody NewAccommodationDTO accommodation) {
        return accommodationService.createAccommodation(accommodation);
    }
}

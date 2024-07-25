package com.codecool.restmates.controller;

import com.codecool.restmates.service.AccommodationService;
import com.codecool.restmates.dto.responses.AccommodationResponseDTO;
import com.codecool.restmates.dto.requests.NewAccommodationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class AccommodationController {
    private final AccommodationService accommodationService;

    @Autowired
    public AccommodationController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @GetMapping(path = "/accommodation/all")
    public List<AccommodationResponseDTO> getAllAccommodation() {
        return accommodationService.findAllAccommodation();
    }

    @GetMapping(path = "/accommodation/{accommodationId}")
    public AccommodationResponseDTO getAccommodation(@PathVariable long accommodationId) {
        return accommodationService.findAccommodationById(accommodationId);
    }

    @PostMapping(path = "/accommodation")
    public AccommodationResponseDTO createAccommodation(@RequestBody NewAccommodationDTO accommodation) {
        return accommodationService.createAccommodation(accommodation);
    }

    @DeleteMapping(path = "/accommodation/{accommodationId}")
    public boolean deleteAccommodationById(@PathVariable long accommodationId) {
        return accommodationService.deleteAccommodationById(accommodationId);
    }
}

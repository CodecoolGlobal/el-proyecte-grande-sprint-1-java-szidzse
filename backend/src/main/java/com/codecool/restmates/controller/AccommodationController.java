package com.codecool.restmates.controller;

import com.codecool.restmates.model.dto.requests.NewAccommodationDTO;
import com.codecool.restmates.model.dto.responses.AccommodationDTO;
import com.codecool.restmates.model.entity.Accommodation;
import com.codecool.restmates.service.AccommodationService;
import com.codecool.restmates.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/api/accommodation")
@AllArgsConstructor
public class AccommodationController {
    private final AccommodationService accommodationService;
    private final ImageService imageService;

    @GetMapping(path = "/all")
    public List<AccommodationDTO> getAllAccommodations() {
        return accommodationService.getAllAccommodations();
    }

    @GetMapping(path = "/{accommodationId}")
    public Accommodation getAccommodationById(@PathVariable Long accommodationId) {
        return accommodationService.getAccommodationById(accommodationId);
    }

    @GetMapping(path = "/{accommodationId}/images")
    public ResponseEntity<List<byte[]>> downloadImagesOfAccommodation(
            @PathVariable Long accommodationId
    ) {
        return accommodationService.getAccommodationImages(accommodationId);
    }

    @PostMapping(path = "")
    public Long createAccommodation(@RequestBody NewAccommodationDTO newAccommodation) {
        return accommodationService.createAccommodation(newAccommodation);
    }

    @PostMapping(path = "/{accommodationId}/image")
    public ResponseEntity<String> uploadImageForAccommodation(
            @PathVariable Long accommodationId,
            @RequestParam("image") MultipartFile file
    ) throws IOException {
        Accommodation accommodation = accommodationService.getAccommodationById(accommodationId);

        String result = imageService.uploadImageForAccommodation(file, accommodation);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping(path = "/{accommodationId}")
    public Long updateAccommodation(@PathVariable Long accommodationId, @RequestBody NewAccommodationDTO newAccommodation) {
        return accommodationService.updateAccommodation(accommodationId, newAccommodation);
    }

    @DeleteMapping(path = "/{accommodationId}")
    public Boolean deleteAccommodation(@PathVariable Long accommodationId) {
        return accommodationService.deleteAccommodation(accommodationId);
    }
}

package com.codecool.restmates.controller;

import com.codecool.restmates.model.dto.requests.NewAccommodationDTO;
import com.codecool.restmates.model.dto.responses.FullAccommodationDTO;
import com.codecool.restmates.model.dto.responses.FullAccommodationWithLocationIdCityStateCountryDTO;
import com.codecool.restmates.model.dto.responses.LessDetailedAccommodationDTO;
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

    @PostMapping(path = "/{accommodationId}/image")
    public ResponseEntity<?> uploadImage (
            @PathVariable Long accommodationId,
            @RequestParam("image") MultipartFile file
    ) throws IOException {
        String uploadResult = imageService.uploadImageForAccommodation(file, accommodationId);

        return ResponseEntity.status(HttpStatus.OK).body(uploadResult);
    }

    @GetMapping(path = "/{accommodationId}/images")
    public ResponseEntity<List<String>> downloadImage(@PathVariable Long accommodationId) throws IOException {
        List<String> images = imageService.downloadImagesOfAccommodation(accommodationId);

        return ResponseEntity.status(HttpStatus.OK).body(images);
    }

    @GetMapping(path = "/all")
    public List<LessDetailedAccommodationDTO> getAllAccommodations() {
        return accommodationService.getAllAccommodations();
    }

    @GetMapping(path = "/{accommodationId}")
    public FullAccommodationWithLocationIdCityStateCountryDTO getAccommodationById(@PathVariable Long accommodationId) {
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
    public Boolean deleteAccommodation(@PathVariable Long accommodationId) {
        return accommodationService.deleteAccommodation(accommodationId);
    }

    @GetMapping("/search")
    public List<LessDetailedAccommodationDTO> searchAccomodationByCountryAndCity(@RequestParam String query) {
        return accommodationService.searchAccommodationByCityAndCountry(query);
    }



}

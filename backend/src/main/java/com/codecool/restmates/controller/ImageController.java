package com.codecool.restmates.controller;

import com.codecool.restmates.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/image")
@AllArgsConstructor
public class ImageController {
    private final ImageService imageService;

    private static final List<String> SUPPORTED_MEDIA_TYPES = List.of(
            "image/png", "image/jpeg", "image/jpg"
    );

    @PostMapping(path = "")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        String contentType = file.getContentType();

        if (!SUPPORTED_MEDIA_TYPES.contains(contentType)) {
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                    .body("Unsupported file type: " + contentType);
        }

        String uploadImage = imageService.uploadImage(file);

        return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
    }

    @GetMapping(path = "/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName) throws IOException {
        byte[] imageData = imageService.downloadImage(fileName);

        String contentType = determineContentType(fileName);

        if (contentType == null) {
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                    .body("Unsupported file type for download.");
        }

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(contentType))
                .body(imageData);
    }

    private String determineContentType(String fileName) {
        if (fileName.endsWith(".png")) {
            return "image/png";
        } else if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
            return "image/jpeg";
        } else {
            return null;
        }
    }
}

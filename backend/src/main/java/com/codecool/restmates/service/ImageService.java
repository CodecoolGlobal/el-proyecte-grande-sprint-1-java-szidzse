package com.codecool.restmates.service;

import com.codecool.restmates.model.entity.Accommodation;
import com.codecool.restmates.model.entity.Image;
import com.codecool.restmates.repository.ImageRepository;
import com.codecool.restmates.util.ImageUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    @Transactional
    public String uploadImage(MultipartFile file) throws IOException {
        Image imageData = imageRepository.save(Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes()))
                .build());

        if (imageData != null) {
            return "File uploaded successfully: " + file.getOriginalFilename();
        }

        return null;
    }

    @Transactional(readOnly = true)
    public byte[] downloadImage(String fileName) throws IOException {
        Optional<Image> dbImageData = imageRepository.findByName(fileName);

        byte[] image = ImageUtils.decompressImage(dbImageData.get().getImageData());

        return image;
    }

    @Transactional
    public String uploadImageForAccommodation(MultipartFile file, Accommodation accommodation) throws IOException {
        Image imageData = imageRepository.save(Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes()))
                .accommodation(accommodation)
                .build());

        if (imageData != null) {
            return "Image successfully uploaded to accommodation: " + file.getOriginalFilename();
        }

        return null;
    }

    @Transactional(readOnly = true)
    public List<byte[]> downloadImagesOfAccommodation(String fileName, Long accommodationId) throws IOException {
        List<Image> dbImagesData = imageRepository.findImagesByAccommodationId(accommodationId);

        if (dbImagesData.isEmpty()) {
            return List.of();
        }

        return dbImagesData.stream()
                .map(dbImageData -> ImageUtils.decompressImage(dbImageData.getImageData()))
                .collect(Collectors.toList());
    }
}

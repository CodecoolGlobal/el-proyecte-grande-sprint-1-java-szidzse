package com.codecool.restmates.service;

import com.codecool.restmates.model.entity.Image;
import com.codecool.restmates.repository.ImageRepository;
import com.codecool.restmates.util.ImageUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    @Transactional
    public String uploadImage(MultipartFile file) throws IOException {
        Image imageData = imageRepository.save(Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());

        if (imageData != null) {
            return "File uploaded successfully: " + file.getOriginalFilename();
        }

        return null;
    }

    @Transactional(readOnly = true)
    public byte[] downloadImage(String fileName) throws IOException {
        Optional<Image> dbImageData = imageRepository.findByName(fileName);

        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());

        return images;
    }
}

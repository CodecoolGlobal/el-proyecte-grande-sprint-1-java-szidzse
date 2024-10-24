package com.codecool.restmates.repository;

import com.codecool.restmates.model.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByName(String fileName);

    List<Image> findAllByAccommodationId(Long accommodationId);
}

package com.codecool.restmates.service;

import com.codecool.restmates.exception.ResourceNotFoundException;
import com.codecool.restmates.model.Accommodation;
import com.codecool.restmates.model.Location;
import com.codecool.restmates.model.Member;
import com.codecool.restmates.repository.AccommodationRepository;
import com.codecool.restmates.repository.LocationRepository;
import com.codecool.restmates.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final LocationRepository locationRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public AccommodationService(AccommodationRepository accommodationRepository, LocationRepository locationRepository, MemberRepository memberRepository) {
        this.accommodationRepository = accommodationRepository;
        this.locationRepository = locationRepository;
        this.memberRepository = memberRepository;
    }

    public List<Accommodation> getAllAccommodations() {
        return accommodationRepository.findAll();
    }

    public Optional<Accommodation> getAccommodationById(Long id) {
        return accommodationRepository.findById(id);
    }

    public Accommodation createAccommodation(Accommodation accommodation, Long ownerId, Long locationId) {
        Member owner = memberRepository.findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found!"));

        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found!"));

        accommodation.setOwner(owner);
        accommodation.setLocation(location);

        return accommodationRepository.save(accommodation);
    }
}

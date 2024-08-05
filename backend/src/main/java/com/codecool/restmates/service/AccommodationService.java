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

    public Optional<Accommodation> getAccommodationById(Long accommodationId) {
        return accommodationRepository.findById(accommodationId);
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

    public Accommodation updateAccommodation(Long accommodationId, Accommodation updatedAccommodation) {
        Accommodation accommodation = accommodationRepository.findById(accommodationId)
                .orElseThrow(() -> new ResourceNotFoundException("Accommodation not found!"));

        accommodation.setName(updatedAccommodation.getName());
        accommodation.setDescription(updatedAccommodation.getDescription());
        accommodation.setRoomNumber(updatedAccommodation.getRoomNumber());
        accommodation.setPricePerNight(updatedAccommodation.getPricePerNight());
        accommodation.setMaxGuests(updatedAccommodation.getMaxGuests());
        accommodation.setAccommodationType(updatedAccommodation.getAccommodationType());

        if (updatedAccommodation.getLocation() != null) {
            accommodation.setLocation(updatedAccommodation.getLocation());
        }

        if (updatedAccommodation.getOwner() != null) {
            accommodation.setOwner(updatedAccommodation.getOwner());
        }

        return accommodationRepository.save(accommodation);
    }

    public boolean deleteAccommodation(Long accommodationId) {
        if (accommodationRepository.existsById(accommodationId)) {
            accommodationRepository.deleteById(accommodationId);
            return true;
        } else {
            throw new ResourceNotFoundException("Accommodation not found with id: " + accommodationId);
        }
    }
}

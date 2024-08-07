package com.codecool.restmates.service;

import com.codecool.restmates.dto.requests.NewAccommodationDTO;
import com.codecool.restmates.dto.responses.AccommodationDTO;
import com.codecool.restmates.dto.responses.LocationCityAndCountryDTO;
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

    public List<AccommodationDTO> getAllAccommodations() {
        List<Accommodation> accommodations = accommodationRepository.findAll();

        return accommodations.stream().map(this::convertToDTO).toList();
    }

    public AccommodationDTO getAccommodationById(Long accommodationId) {
        Optional<Accommodation> accommodation = accommodationRepository.findById(accommodationId);

        if (accommodation.isPresent()) {
            Accommodation accommodationEntity = accommodation.get();

            return convertToDTO(accommodationEntity);
        } else {
            throw new ResourceNotFoundException(String.format("Accommodation with id %s not found!", accommodationId));
        }
    }

    public Long createAccommodation(NewAccommodationDTO newAccommodationDTO) {
        Long ownerId = newAccommodationDTO.ownerId();
        Long locationId = newAccommodationDTO.locationId();

        Member owner = memberRepository.findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found with id: " + ownerId));

        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found with id: " + locationId));

        Accommodation accommodation = new Accommodation();
        accommodation.setName(newAccommodationDTO.name());
        accommodation.setDescription(newAccommodationDTO.description());
        accommodation.setRoomNumber(newAccommodationDTO.roomNumber());
        accommodation.setPricePerNight(newAccommodationDTO.pricePerNight());
        accommodation.setMaxGuests(newAccommodationDTO.maxGuests());
        accommodation.setOwner(owner);
        accommodation.setLocation(location);

        accommodationRepository.save(accommodation);

        return accommodation.getId();
    }

    public Accommodation updateAccommodation(Long accommodationId, Accommodation updatedAccommodation) {
        Accommodation accommodation = accommodationRepository.findById(accommodationId)
                .orElseThrow(() -> new ResourceNotFoundException("Accommodation not found with id: " + accommodationId));

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

    private AccommodationDTO convertToDTO(Accommodation accommodation) {
        LocationCityAndCountryDTO locationDTO = new LocationCityAndCountryDTO(
                accommodation.getLocation().getCity(),
                accommodation.getLocation().getCountry()
        );

        return new AccommodationDTO(
                accommodation.getName(),
                accommodation.getDescription(),
                accommodation.getRoomNumber(),
                accommodation.getPricePerNight(),
                accommodation.getMaxGuests(),
                locationDTO
        );
    }

}

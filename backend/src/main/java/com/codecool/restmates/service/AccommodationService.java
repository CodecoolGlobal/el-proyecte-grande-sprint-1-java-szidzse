package com.codecool.restmates.service;

import com.codecool.restmates.model.dto.requests.NewAccommodationDTO;
import com.codecool.restmates.model.dto.responses.*;
import com.codecool.restmates.exception.MemberNoRightsException;
import com.codecool.restmates.exception.ResourceNotFoundException;
import com.codecool.restmates.model.entity.Accommodation;
import com.codecool.restmates.model.entity.Location;
import com.codecool.restmates.model.entity.Member;
import com.codecool.restmates.repository.AccommodationRepository;
import com.codecool.restmates.repository.LocationRepository;
import com.codecool.restmates.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final LocationRepository locationRepository;
    private final MemberRepository memberRepository;

    public List<LessDetailedAccommodationDTO> getAllAccommodations() {
        List<Accommodation> accommodations = accommodationRepository.findAll();

        return accommodations.stream().map(this::convertToLessDetailedDTO).toList();
    }

    public FullAccommodationWithLocationIdCityStateCountryDTO getAccommodationById(Long accommodationId) {
        Optional<Accommodation> accommodation = accommodationRepository.findById(accommodationId);

        if (accommodation.isPresent()) {
            Accommodation accommodationEntity = accommodation.get();

            return convertToFullAccommodationWithLocationIdCityStateCountryDTO(accommodationEntity);
        } else {
            throw new ResourceNotFoundException(String.format("Accommodation with id %s not found!", accommodationId));
        }
    }

    public List<LessDetailedAccommodationDTO> searchAccommodationByCityAndCountry(String query) {
        List<Accommodation> accommodations = accommodationRepository.findByLocationCityStartingWithIgnoreCaseOrLocationCountryStartingWithIgnoreCase(query, query);


        return accommodations.stream()
                .map(accommodation -> new LessDetailedAccommodationDTO(
                        accommodation.getId(),
                        accommodation.getName(),
                        accommodation.getDescription(),
                        accommodation.getPricePerNight(),
                        new LocationCityStateCountryDTO(accommodation.getLocation().getCity(), accommodation.getLocation().getState(), accommodation.getLocation().getCountry())
                ))
                .toList();
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

    public Long updateAccommodation(Long accommodationId, NewAccommodationDTO updatedAccommodation) {
        Accommodation accommodation = accommodationRepository.findById(accommodationId)
                .orElseThrow(() -> new ResourceNotFoundException("Accommodation not found with id: " + accommodationId));

        accommodation.setName(updatedAccommodation.name());
        accommodation.setDescription(updatedAccommodation.description());
        accommodation.setRoomNumber(updatedAccommodation.roomNumber());
        accommodation.setPricePerNight(updatedAccommodation.pricePerNight());
        accommodation.setMaxGuests(updatedAccommodation.maxGuests());

        Long ownerId = updatedAccommodation.ownerId();
        Member owner = memberRepository.findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + ownerId));


        if (updatedAccommodation.ownerId() != owner.getId()) {
            throw new MemberNoRightsException("Member no right to do this action!");
        }

        accommodationRepository.save(accommodation);
        return accommodation.getId();
    }

    public Boolean deleteAccommodation(Long accommodationId) {
        if (accommodationRepository.existsById(accommodationId)) {
            accommodationRepository.deleteById(accommodationId);
            return true;
        } else {
            throw new ResourceNotFoundException("Accommodation not found with id: " + accommodationId);
        }
    }

    private LessDetailedAccommodationDTO convertToLessDetailedDTO(Accommodation accommodation) {
        LocationCityStateCountryDTO locationDTO = new LocationCityStateCountryDTO(
                accommodation.getLocation().getCity(),
                accommodation.getLocation().getState(),
                accommodation.getLocation().getCountry()
        );

        return new LessDetailedAccommodationDTO(
                accommodation.getId(),
                accommodation.getName(),
                accommodation.getDescription(),
                accommodation.getPricePerNight(),
                locationDTO
        );
    }

    private FullAccommodationDTO convertToFullDTO(Accommodation accommodation) {
        LocationCityStateCountryDTO locationDTO = new LocationCityStateCountryDTO(
                accommodation.getLocation().getCity(),
                accommodation.getLocation().getState(),
                accommodation.getLocation().getCountry()
        );

        return new FullAccommodationDTO(
                accommodation.getId(),
                accommodation.getName(),
                accommodation.getDescription(),
                accommodation.getRoomNumber(),
                accommodation.getPricePerNight(),
                accommodation.getMaxGuests(),
                locationDTO
        );
    }

    private FullAccommodationWithLocationIdCityStateCountryDTO convertToFullAccommodationWithLocationIdCityStateCountryDTO(Accommodation accommodation) {
        LocationIdCityStateCountryDTO locationDTO = new LocationIdCityStateCountryDTO(
                accommodation.getLocation().getId(),
                accommodation.getLocation().getCity(),
                accommodation.getLocation().getState(),
                accommodation.getLocation().getCountry()
        );

        return new FullAccommodationWithLocationIdCityStateCountryDTO(
                accommodation.getId(),
                accommodation.getName(),
                accommodation.getDescription(),
                accommodation.getRoomNumber(),
                accommodation.getPricePerNight(),
                accommodation.getMaxGuests(),
                locationDTO
        );
    }

}

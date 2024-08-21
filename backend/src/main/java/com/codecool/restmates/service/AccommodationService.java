package com.codecool.restmates.service;

import com.codecool.restmates.model.dto.requests.NewAccommodationDTO;
import com.codecool.restmates.model.dto.responses.AccommodationDTO;
import com.codecool.restmates.model.dto.responses.LocationCityAndCountryDTO;
import com.codecool.restmates.exception.MemberNoRightsException;
import com.codecool.restmates.exception.ResourceNotFoundException;
import com.codecool.restmates.model.entity.Accommodation;
import com.codecool.restmates.model.entity.Location;
import com.codecool.restmates.model.entity.Member;
import com.codecool.restmates.repository.AccommodationRepository;
import com.codecool.restmates.repository.LocationRepository;
import com.codecool.restmates.repository.MemberRepository;
import com.codecool.restmates.util.ImageUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final LocationRepository locationRepository;
    private final MemberRepository memberRepository;

    public List<AccommodationDTO> getAllAccommodations() {
        List<Accommodation> accommodations = accommodationRepository.findAll();

        return accommodations.stream().map(this::convertToDTO).toList();
    }

    public Accommodation getAccommodationById(Long accommodationId) {
        Optional<Accommodation> accommodation = accommodationRepository.findById(accommodationId);

        if (accommodation.isPresent()) {
            Accommodation accommodationEntity = accommodation.get();

            return accommodationEntity;
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

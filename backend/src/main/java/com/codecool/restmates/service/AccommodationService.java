package com.codecool.restmates.service;

import com.codecool.restmates.model.dto.requests.NewAccommodationDTO;
import com.codecool.restmates.model.dto.requests.NewLocationDTO;
import com.codecool.restmates.model.dto.requests.UpdateAccommodationDTO;
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
import java.util.stream.Collectors;

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

    public List<LessDetailedAccommodationDTO> getAllAccommodationsByMember(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);

        if (member.isPresent()) {
            Long memberId = member.get().getId();
            List<Accommodation> accommodations = accommodationRepository.findAll();

            return accommodations.stream()
                    .filter(accommodation -> accommodation.getOwner().getId().equals(memberId))
                    .map(accommodation -> convertToLessDetailedDTO(accommodation))
                    .collect(Collectors.toList());
        } else {
            throw new ResourceNotFoundException(String.format("Member with email %s not found!", email));
        }
    }


    public Long createAccommodation(NewAccommodationDTO newAccommodation, String email) {
        Optional<Member> member = memberRepository.findByEmail(email);

        if (member.isPresent()) {
            Location location = new Location(
                    newAccommodation.location().street(),
                    newAccommodation.location().city(),
                    newAccommodation.location().state(),
                    newAccommodation.location().country(),
                    newAccommodation.location().zipCode()
            );

            locationRepository.save(location);

            Accommodation accommodation = new Accommodation();
            accommodation.setName(newAccommodation.name());
            accommodation.setDescription(newAccommodation.description());
            accommodation.setRoomNumber(newAccommodation.roomNumber());
            accommodation.setPricePerNight(newAccommodation.pricePerNight());
            accommodation.setMaxGuests(newAccommodation.maxGuests());
            accommodation.setLocation(location);
            accommodation.setOwner(member.get());

            accommodationRepository.save(accommodation);

            return accommodation.getId();
        } else {
            throw new ResourceNotFoundException(String.format("Member with email %s not found!", email));
        }
    }

    public Long updateAccommodation(Long accommodationId, UpdateAccommodationDTO updateAccommodationDTO, String email) {
        Accommodation accommodation = accommodationRepository.findById(accommodationId)
                .orElseThrow(() -> new ResourceNotFoundException("Accommodation not found with id: " + accommodationId));

        Optional<Member> member = memberRepository.findByEmail(email);

        Long locationId = updateAccommodationDTO.locationId();
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found with id: " + locationId));


        if (member.isPresent()) {
            accommodation.setName(updateAccommodationDTO.name());
            accommodation.setDescription(updateAccommodationDTO.description());
            accommodation.setRoomNumber(updateAccommodationDTO.roomNumber());
            accommodation.setPricePerNight(updateAccommodationDTO.pricePerNight());
            accommodation.setMaxGuests(updateAccommodationDTO.maxGuests());
            accommodation.setLocation(location);
            accommodation.setOwner(member.get());
            accommodationRepository.save(accommodation);
            return accommodation.getId();
        } else {
            throw new ResourceNotFoundException(String.format("Member with email %s not found!", email));
        }
    }

    public Boolean deleteAccommodation(Long accommodationId, String email) {
        Optional<Member> member = memberRepository.findByEmail(email);

        if (member.isPresent()) {
            if (accommodationRepository.existsById(accommodationId)) {
                accommodationRepository.deleteById(accommodationId);
                return true;
            } else {
                throw new ResourceNotFoundException("Accommodation not found with id: " + accommodationId);
            }
        } else {
            throw new ResourceNotFoundException(String.format("Member with email %s not found!", email));
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

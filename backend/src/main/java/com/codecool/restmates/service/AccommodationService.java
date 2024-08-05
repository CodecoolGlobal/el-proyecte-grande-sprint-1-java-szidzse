package com.codecool.restmates.service;

import com.codecool.restmates.model.Accommodation;
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
}

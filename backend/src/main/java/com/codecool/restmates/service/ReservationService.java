package com.codecool.restmates.service;

import com.codecool.restmates.dto.requests.NewReservationWithBothIDsDTO;
import com.codecool.restmates.dto.responses.ReservationDTO;
import com.codecool.restmates.exception.ResourceNotFoundException;
import com.codecool.restmates.model.Reservation;
import com.codecool.restmates.repository.AccommodationRepository;
import com.codecool.restmates.repository.MemberRepository;
import com.codecool.restmates.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final AccommodationRepository accommodationRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, AccommodationRepository accommodationRepository, MemberRepository memberRepository) {
        this.reservationRepository = reservationRepository;
        this.accommodationRepository = accommodationRepository;
        this.memberRepository = memberRepository;
    }

    public ReservationDTO getReservationById(Long reservationId) {
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);

        if (reservation.isPresent()) {
            Reservation reservationEntity = reservation.get();

            return convertToDTO(reservationEntity);
        } else {
            throw new ResourceNotFoundException(String.format("Reservation with id %s not found!", reservationId));
        }
    }

    public Long createReservation(NewReservationWithBothIDsDTO newReservation) {
        Long guestId = newReservation.guestId();
        Long accommodationId = newReservation.accommodationId();

        memberRepository.findById(guestId)
                .orElseThrow(() -> new ResourceNotFoundException("Guest not found with id: " + guestId));

        accommodationRepository.findById(accommodationId)
                .orElseThrow(() -> new ResourceNotFoundException("Accommodation not found with id: " + accommodationId));

        Reservation reservation = new Reservation();
        reservation.setStartDate(newReservation.startDate());
        reservation.setEndDate(newReservation.endDate());

        reservationRepository.save(reservation);

        return reservation.getId();
    }

    public Boolean deleteReservation(Long reservationId) {
        if (reservationRepository.existsById(reservationId)) {
            reservationRepository.deleteById(reservationId);
            return true;
        } else {
            throw new ResourceNotFoundException("Reservation not found with id: " + reservationId);
        }
    }

    private ReservationDTO convertToDTO(Reservation reservation) {
        return new ReservationDTO(
                reservation.getStartDate(),
                reservation.getEndDate()
        );
    }
}

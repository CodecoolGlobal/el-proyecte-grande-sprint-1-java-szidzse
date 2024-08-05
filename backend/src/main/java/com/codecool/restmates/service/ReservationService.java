package com.codecool.restmates.service;

import com.codecool.restmates.exception.ResourceNotFoundException;
import com.codecool.restmates.model.Accommodation;
import com.codecool.restmates.model.Member;
import com.codecool.restmates.model.Reservation;
import com.codecool.restmates.repository.AccommodationRepository;
import com.codecool.restmates.repository.MemberRepository;
import com.codecool.restmates.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> getReservationById(Long reservationId) {
        return reservationRepository.findById(reservationId);
    }

    public Reservation createReservation(Reservation reservation, Long accommodationId, Long guestId) {
        Accommodation accommodation = accommodationRepository.findById(accommodationId)
                .orElseThrow(() -> new ResourceNotFoundException("Accommodation not found with id: " + accommodationId));

        Member guest = memberRepository.findById(guestId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + guestId));

        reservation.setAccommodation(accommodation);
        reservation.setGuest(guest);

        return reservationRepository.save(reservation);
    }

    public Reservation updateReservation(Long reservationId, Reservation updatedReservation) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with id: " + reservationId));

        reservation.setStartDate(updatedReservation.getStartDate());
        reservation.setEndDate(updatedReservation.getEndDate());

        if (updatedReservation.getAccommodation() != null) {
            reservation.setAccommodation(updatedReservation.getAccommodation());
        }
        if (updatedReservation.getGuest() != null) {
            reservation.setGuest(updatedReservation.getGuest());
        }

        return reservationRepository.save(reservation);
    }

    public boolean deleteReservation(Long reservationId) {
        if (reservationRepository.existsById(reservationId)) {
            reservationRepository.deleteById(reservationId);
            return true;
        } else {
            throw new ResourceNotFoundException("Reservation not found with id: " + reservationId);
        }
    }
}

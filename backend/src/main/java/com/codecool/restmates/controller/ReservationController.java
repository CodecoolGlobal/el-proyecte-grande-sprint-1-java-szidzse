package com.codecool.restmates.controller;

import com.codecool.restmates.exception.ResourceNotFoundException;
import com.codecool.restmates.model.Reservation;
import com.codecool.restmates.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long reservationId) {
        Optional<Reservation> reservation = reservationService.getReservationById(reservationId);
        return reservation.map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with id: " + reservationId));
    }

    @PostMapping(path = "")
    public ResponseEntity<Reservation> createReservation(
            @RequestBody Reservation reservation,
            @RequestParam Long accommodationId,
            @RequestParam Long guestId) {
        Reservation createdReservation = reservationService.createReservation(reservation, accommodationId, guestId);
        return ResponseEntity.ok(createdReservation);
    }

    @PutMapping(path = "/{reservationId}")
    public ResponseEntity<Reservation> updateReservation(
            @PathVariable Long reservationId,
            @RequestBody Reservation updatedReservation) {
        Reservation reservation = reservationService.updateReservation(reservationId, updatedReservation);
        return ResponseEntity.ok(reservation);
    }

    @DeleteMapping(path = "/{reservationId}")
    public ResponseEntity<Boolean> deleteReservation(@PathVariable Long reservationId) {
        boolean isDeleted = reservationService.deleteReservation(reservationId);
        return ResponseEntity.ok(isDeleted);
    }
}

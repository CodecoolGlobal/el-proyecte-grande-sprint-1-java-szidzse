package com.codecool.restmates.controller;

import com.codecool.restmates.model.dto.requests.NewReservationWithBothIDsDTO;
import com.codecool.restmates.model.dto.responses.ReservationDTO;
import com.codecool.restmates.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/{reservationId}")
    public ReservationDTO getReservationById(@PathVariable Long reservationId) {
        return reservationService.getReservationById(reservationId);
    }

    @PostMapping(path = "")
    public Long createReservation(@RequestBody NewReservationWithBothIDsDTO newReservation) {
        return reservationService.createReservation(newReservation);
    }

    @DeleteMapping(path = "/{reservationId}")
    public Boolean deleteReservation(@PathVariable Long reservationId) {
        return reservationService.deleteReservation(reservationId);
    }
}

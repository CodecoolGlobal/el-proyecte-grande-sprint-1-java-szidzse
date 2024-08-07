package com.codecool.restmates.dto.requests;

import java.time.LocalDate;

public record NewReservationDTO(LocalDate startDate, LocalDate endDate, Long accommodationId) {
}

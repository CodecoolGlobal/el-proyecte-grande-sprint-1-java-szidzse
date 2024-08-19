package com.codecool.restmates.model.dto.requests;

import java.time.LocalDate;

public record NewReservationWithBothIDsDTO(LocalDate startDate, LocalDate endDate, Long guestId, Long accommodationId) {
}

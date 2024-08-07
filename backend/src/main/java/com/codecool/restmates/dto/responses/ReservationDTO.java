package com.codecool.restmates.dto.responses;

import java.time.LocalDate;

public record ReservationDTO(LocalDate startDate, LocalDate endDate) {
}

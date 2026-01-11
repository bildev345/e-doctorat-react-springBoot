package org.example.doctoratrestapi.dtos.commission;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

public record CommissionCreationDto(
        LocalDate dateCommission,
        String lieu,
        Time heure,
        long laboId,
        List<Long> profIds
) {
}

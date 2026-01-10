package org.example.doctoratrestapi.dtos.commission;

import jakarta.persistence.*;

import org.example.doctoratrestapi.dtos.labo.LaboratoireDTO;
import org.example.doctoratrestapi.dtos.professeur.ProfesseurDto;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;


public record CommissionDTO(
        long commissionId,
        LocalDate dateCommission,
        String lieu,
        Time heure,
        long laboId,
        LaboratoireDTO laboratoireDto,
        List <ProfesseurDto> professeurDtos) {
}

package org.example.doctoratrestapi.inscription.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor

// DTO de création (requestDTO)
public class CandidatInscriptionCreationDto {
    private LocalDate dateDeposerDossier;
    private Long candidatId;
    private Long sujetId;
}

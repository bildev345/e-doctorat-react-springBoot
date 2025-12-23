package org.example.doctoratrestapi.examiner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ExaminationCreationDTO {
    private Long candidatId;
    private Long commissionId;
    private Long sujetId;
    private String decision;
    private float noteDossier;
    private int noteEntretien;
    private boolean publier;
    private boolean valider;
}

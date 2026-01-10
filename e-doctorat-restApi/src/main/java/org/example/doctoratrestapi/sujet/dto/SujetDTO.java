package org.example.doctoratrestapi.sujet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class SujetDTO {
    private Long sujetId;
    private String titre;
    private String description;
    private boolean publier;

    private Long formationDoctoralId;
    private String titreFormationDoctoral;
}

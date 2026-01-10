package org.example.doctoratrestapi.sujet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class SujetDtoCreation {
    private String titre;
    private String description;
    private boolean publier;
    private Long coDirecteurId;
    private Long FormationDoctoralId;
}

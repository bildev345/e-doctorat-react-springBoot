package org.example.doctoratrestapi.sujet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.doctoratrestapi.formationdoctorale.FormationDoctoraleModel;
import org.example.doctoratrestapi.professeur.ProfesseurModel;

@Data
@AllArgsConstructor

public class SujetDtoCreation {
    private String titre;
    private String description;
    private boolean publier;
    private Long coDirecteurId;
    private Long FormationDoctoralId;
}

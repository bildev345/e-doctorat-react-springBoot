package org.example.doctoratrestapi.sujet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.doctoratrestapi.examiner.ExaminerModel;
import org.example.doctoratrestapi.inscription.InscriptionModel;

import java.util.List;

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

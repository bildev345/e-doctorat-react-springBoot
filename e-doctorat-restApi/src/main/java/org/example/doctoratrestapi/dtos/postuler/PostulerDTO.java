package org.example.doctoratrestapi.dtos.postuler;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PostulerDTO {
    //postuler
    private String pathFile;
    //candidat
    private String cne;
    private String cin;
    private String nomCandidatArabe;
    private String prenomCandidatArabe;
    //sujet
    private String titre;
    private String description;
    private boolean publier;


}

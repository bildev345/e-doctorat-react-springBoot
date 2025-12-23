package org.example.doctoratrestapi.candidat;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.doctoratrestapi.diplome.DiplomeModel;

import java.util.List;

@Data
@AllArgsConstructor

public class CandidatDTO {
    private String cne;
    private String cin;
    private String nomCandidatArabe;
    private String prenomCandidatArabe;
    private String adresse;
    private List<DiplomeModel> diplomes;
}

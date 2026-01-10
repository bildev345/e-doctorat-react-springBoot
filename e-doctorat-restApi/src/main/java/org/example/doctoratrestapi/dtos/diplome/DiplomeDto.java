package org.example.doctoratrestapi.dtos.diplome;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.doctoratrestapi.models.AnnexeModel;

import java.util.List;

@Data
@AllArgsConstructor
public class DiplomeDto {
    // diplome attributs
    private String etalissement;
    private String intitule;
    private String mention;
    private double moyenneGenerale;
    private String ville;
    // annexes
    private List<AnnexeModel> annexes;
}

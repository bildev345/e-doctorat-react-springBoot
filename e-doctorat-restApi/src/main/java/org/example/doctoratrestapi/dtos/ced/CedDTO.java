package org.example.doctoratrestapi.dtos.ced;

import org.example.doctoratrestapi.dtos.professeur.ProfesseurDto;


public record CedDTO(
        Long id,
        String titre,
        String initiale,
        String description,
        ProfesseurDto directeur,
        int nombreFormations
) {

}

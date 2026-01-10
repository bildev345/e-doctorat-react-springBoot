package org.example.doctoratrestapi.dtos.labo;

import org.example.doctoratrestapi.dtos.ced.CedDTO;
import org.example.doctoratrestapi.dtos.etablissement.EtablissementDTO;
import org.example.doctoratrestapi.dtos.professeur.ProfesseurDto;

public record LaboratoireDTO(
        Long laboId,
        String nomLaboratoire,
        String description,

        CedDTO cedDto,
        EtablissementDTO etablissementDto
) {

}

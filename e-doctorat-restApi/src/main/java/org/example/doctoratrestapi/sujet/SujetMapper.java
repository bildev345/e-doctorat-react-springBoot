package org.example.doctoratrestapi.sujet;

import org.example.doctoratrestapi.models.FormationDoctoraleModel;
import org.example.doctoratrestapi.sujet.dto.SujetDTO;
import org.example.doctoratrestapi.sujet.dto.SujetDtoCreation;
import org.springframework.stereotype.Component;

@Component
public class SujetMapper {
    public SujetDTO toDTO(SujetModel sujet){
        Long sujetId = sujet.getId();
        String titre = sujet.getTitre();
        String description = sujet.getDescription();
        boolean publier = sujet.isPublier();
        Long formationDoctoralId = sujet.getFormation().getId();
        String titreFormationDoctoral = sujet.getFormation().getTitre();
        return new SujetDTO(sujetId, titre, description, publier, formationDoctoralId, titreFormationDoctoral);
    }

    public SujetModel toSujet(SujetDtoCreation sujetDtoCreation, FormationDoctoraleModel formation){
          SujetModel sujet = new SujetModel();
          sujet.setFormation(formation);
          sujet.setTitre(sujetDtoCreation.getTitre());
          sujet.setDescription(sujetDtoCreation.getDescription());
          sujet.setPublier(sujetDtoCreation.isPublier());
          return sujet;
    }
}

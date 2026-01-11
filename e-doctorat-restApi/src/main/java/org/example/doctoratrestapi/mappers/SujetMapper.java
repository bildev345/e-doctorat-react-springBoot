package org.example.doctoratrestapi.mappers;

import org.example.doctoratrestapi.models.FormationDoctoraleModel;
import org.example.doctoratrestapi.models.ProfesseurModel;
import org.example.doctoratrestapi.models.SujetModel;
import org.example.doctoratrestapi.dtos.sujet.SujetDTO;
import org.example.doctoratrestapi.dtos.sujet.SujetDtoCreation;
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

    public SujetModel toSujet(SujetDtoCreation sujetDtoCreation, FormationDoctoraleModel formation, ProfesseurModel coDirecteur){
        SujetModel sujet = new SujetModel();
        if(coDirecteur != null){
            sujet.getCoDirecteur().setId(coDirecteur.getId());
        }
        sujet.setFormation(formation);
        sujet.setTitre(sujetDtoCreation.getTitre());
        sujet.setDescription(sujetDtoCreation.getDescription());
        sujet.setFormation(formation);
        //sujet.setPublier(sujetDtoCreation.isPublier());
        return sujet;
    }
}

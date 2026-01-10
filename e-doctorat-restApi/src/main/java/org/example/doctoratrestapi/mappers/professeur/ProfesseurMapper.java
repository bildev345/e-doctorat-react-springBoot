package org.example.doctoratrestapi.mappers.professeur;

import org.example.doctoratrestapi.dtos.professeur.ProfesseurDto;
import org.example.doctoratrestapi.models.ProfesseurModel;
import org.springframework.stereotype.Component;

@Component
public class ProfesseurMapper {
    public ProfesseurDto toDto(ProfesseurModel prof){
        String nom = prof.getUser().getLastName();
        String prenom = prof.getUser().getFirstName();
        String grade = prof.getGrade();
        return new ProfesseurDto(nom, prenom, grade);
    }

}

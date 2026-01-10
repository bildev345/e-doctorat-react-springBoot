package org.example.doctoratrestapi.mappers.ced;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import org.example.doctoratrestapi.dtos.ced.CedDTO;
import org.example.doctoratrestapi.dtos.formationDoctorale.FormationDoctoraleDTO;
import org.example.doctoratrestapi.dtos.professeur.ProfesseurDto;
import org.example.doctoratrestapi.mappers.professeur.ProfesseurMapper;
import org.example.doctoratrestapi.models.CedModel;
import org.example.doctoratrestapi.models.FormationDoctoraleModel;
import org.example.doctoratrestapi.models.ProfesseurModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CedMapper {
    private final ProfesseurMapper profMapper;
    public CedMapper(ProfesseurMapper profMapper) {
        this.profMapper = profMapper;
    }
    // CedModel → CedDTO (les infos CED détaillé)
    public CedDTO toDTO(CedModel ced) {
        Long cedId = ced.getId();
        String titre = ced.getTitre();
        String initiale = ced.getInitiale();
        String description = ced.getDescription();
        ProfesseurDto directeur = profMapper.toDto(ced.getDirecteur());
        int nombreFormations = ced.getFormations().size();

        return new CedDTO(cedId, titre, initiale, description, directeur, nombreFormations);
    }

    // (tous CED)
    public List<CedDTO> toDTOs(List<CedModel> ceds) {
        return ceds.stream()
                .map(ced -> {
                    ProfesseurDto directeurDto = profMapper.toDto(ced.getDirecteur());
                    return new CedDTO(ced.getId(), ced.getTitre(), ced.getInitiale(), ced.getDescription(), directeurDto, ced.getFormations().size());
                })
                .collect(Collectors.toList());
    }

    // FormationDoctoraleModel(pour /ceds/{id}/formations)
    public FormationDoctoraleDTO toFormationDTO(FormationDoctoraleModel formation) {
        return FormationDoctoraleDTO.builder()
                .id(formation.getId())
                .titre(formation.getTitre())
                .build();
    }

    //List formations
    public List<FormationDoctoraleDTO> toFormationDTOs(List<FormationDoctoraleModel> formations) {
        return formations.stream()
                .map(this::toFormationDTO)
                .collect(Collectors.toList());
    }



}

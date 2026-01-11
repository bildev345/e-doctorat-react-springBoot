package org.example.doctoratrestapi.professeur.professeurServices;

import org.example.doctoratrestapi.models.FormationDoctoraleModel;
import org.example.doctoratrestapi.formationdoctorale.FormationDoctoraleRepository;
import org.example.doctoratrestapi.mappers.SujetMapper;
import org.example.doctoratrestapi.models.SujetModel;
import org.example.doctoratrestapi.sujet.SujetRepository;
import org.example.doctoratrestapi.dtos.sujet.SujetDTO;
import org.example.doctoratrestapi.dtos.sujet.SujetDtoCreation;
import org.example.doctoratrestapi.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SujetService {
    private final FormationDoctoraleRepository formationRep;
    private final SujetRepository sujetRep;
    private final SujetMapper mapper;

    public SujetService(FormationDoctoraleRepository formationDoctoraleRepository, SujetRepository sujetRep,  SujetMapper mapper) {
        this.formationRep = formationDoctoraleRepository;
        this.sujetRep = sujetRep;
        this.mapper = mapper;
    }

    //ajouter un sujet
    public SujetDTO addSujet(SujetDtoCreation sujetDtoCreation){
        long profUserId = SecurityUtils.currentUserId();

        FormationDoctoraleModel formation = formationRep.findById(sujetDtoCreation.getFormationDoctoralId())
                .orElseThrow(() -> new RuntimeException("La formation doctorale est introuvable!!!"));

        SujetModel sujet = mapper.toSujet(sujetDtoCreation, formation);
        SujetModel createdSujet = sujetRep.save(sujet);
        return mapper.toDTO(createdSujet);
    }
    // Supprimer un sujet
    public void deleteSujet(Long professeurId, Long sujetId) {
        SujetModel sujet = sujetRep.findById(sujetId)
                .orElseThrow(() -> new RuntimeException("Sujet introuvable"));

        sujetRep.delete(sujet);
    }
    // prof consulte ces propres sujets
    public List<SujetDTO> getSujetsByProfesseur(Long professeurId) {
        return sujetRep.findByProfesseurId(professeurId)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
    // sujet by id
    public SujetDTO getSujetById(Long sujetId) {
        SujetModel sujet = sujetRep.findById(sujetId)
                .orElseThrow(() -> new RuntimeException("Sujet introuvable"));
        return mapper.toDTO(sujet);
    }
    // Modifier un sujet
    public SujetDTO updateSujet(Long professeurId, Long sujetId, SujetDtoCreation sujetDtoCreation) {
        SujetModel sujet = sujetRep.findById(sujetId)
                .orElseThrow(() -> new RuntimeException("Sujet introuvable"));

        // update rows
        sujet.setTitre(sujetDtoCreation.getTitre());
        sujet.setDescription(sujetDtoCreation.getDescription());
        sujet.setPublier(sujetDtoCreation.isPublier());

        SujetModel updated = sujetRep.save(sujet);
        return mapper.toDTO(updated);
    }


}

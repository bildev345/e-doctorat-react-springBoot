package org.example.doctoratrestapi.sujet;

import org.example.doctoratrestapi.mappers.SujetMapper;
import org.example.doctoratrestapi.models.FormationDoctoraleModel;
import org.example.doctoratrestapi.formationdoctorale.FormationDoctoraleRepository;
import org.example.doctoratrestapi.models.ProfesseurModel;
import org.example.doctoratrestapi.models.SujetModel;
import org.example.doctoratrestapi.professeur.ProfesseurRepository;
import org.example.doctoratrestapi.dtos.sujet.SujetDTO;
import org.example.doctoratrestapi.dtos.sujet.SujetDtoCreation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SujetService {
    private List <SujetModel> sujets;
    @Autowired
    private ProfesseurRepository profRep;
    @Autowired
    private FormationDoctoraleRepository formationRep;
    @Autowired
    private SujetRepository sujetRep;
    @Autowired
    private SujetMapper mapper;

//    public SujetDTO addSujet(SujetDtoCreation sujetDtoCreation, Long professeurId){
//        ProfesseurModel professeur = profRep.findById(professeurId)
//                .orElseThrow(() -> new RuntimeException("Candidat est introuvable!!!"));
//        ProfesseurModel coDirecteur = profRep.findById(sujetDtoCreation.getCoDirecteurId())
//                .orElseThrow(() -> new RuntimeException("Le coDirecteur est introuvable!!!"));
//        FormationDoctoraleModel formation = formationRep.findById(sujetDtoCreation.getFormationDoctoralId())
//                .orElseThrow(() -> new RuntimeException("La formation doctorale est introuvable!!!"));
//
//        SujetModel sujet = mapper.toSujet(sujetDtoCreation, coDirecteur, professeur, formation);
//        SujetModel createdSujet = sujetRep.save(sujet);
//        return mapper.toDTO(createdSujet);
//    }
//    public List<SujetDTO> getSujetsByLaboId(Long laboId){
//        return sujetRep.findAll()
//                .stream()
//                .map(mapper::toDTO)
//                .collect(Collectors.toList());
//    }
    // Supprimer un sujet
    public void deleteSujet(Long professeurId, Long sujetId) {
        SujetModel sujet = sujetRep.findById(sujetId)
                .orElseThrow(() -> new RuntimeException("Sujet introuvable"));

        sujetRep.delete(sujet);
    }
}

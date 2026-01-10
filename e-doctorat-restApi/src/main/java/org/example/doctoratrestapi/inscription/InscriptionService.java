package org.example.doctoratrestapi.inscription;


import org.example.doctoratrestapi.mappers.inscription.InscriptionMapper;
import org.example.doctoratrestapi.models.CandidatModel;
import org.example.doctoratrestapi.candidat.CandidatRepository;
import org.example.doctoratrestapi.dtos.inscription.CandidatInscriptionCreationDto;
import org.example.doctoratrestapi.dtos.inscription.CandidatInscriptionDto;
import org.example.doctoratrestapi.models.InscriptionModel;
import org.example.doctoratrestapi.sujet.SujetModel;
import org.example.doctoratrestapi.sujet.SujetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InscriptionService {

    @Autowired
    private  CandidatRepository candidatRep;
    @Autowired
    private  SujetRepository sujetRep;
    @Autowired
    private InscriptionRepository inscriptionRep; 
    @Autowired
    private InscriptionMapper mapper;
    public CandidatInscriptionDto addInscription(CandidatInscriptionCreationDto dtoCreation) {
        CandidatModel candidat = candidatRep.findById(dtoCreation.getCandidatId())
            .orElseThrow(() -> new RuntimeException("Candidat introuvable"));
        SujetModel sujet = sujetRep.findById(dtoCreation.getSujetId())
            .orElseThrow(() -> new RuntimeException("Sujet introuvable"));

        if (inscriptionRep.existsByCandidatId(candidat.getId())) {
            throw new RuntimeException("Le candidat a déja inscrit!!!");
        }

        InscriptionModel inscription = mapper.toInscription(dtoCreation, candidat, sujet);
        InscriptionModel saved = inscriptionRep.save(inscription);
        return mapper.toDto(saved);
}
    // Prof consulte les inscriptions de son sujet
    public List<CandidatInscriptionDto> getInscriptionBySujet(Long sujetId) {
        return inscriptionRep.findInscriptionModelsBySujet_Id(sujetId)
                .stream()
                .map(mapper::toDto)
                .toList();
    }
    public List<CandidatInscriptionDto> getAllInscriptions() {
        return inscriptionRep.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public CandidatInscriptionDto getInscriptionById(Long id) {
        return mapper.toDto(inscriptionRep.findById(id).orElseThrow(() ->
                new RuntimeException("Inscription non trouvée avec l'ID: " + id)
        ));
    }
    public List<InscriptionModel> deleteInscription(Long id) {
        if (!inscriptionRep.existsById(id)) {
            throw new RuntimeException("Inscription non trouvée avec l'ID: " + id);
        }
        inscriptionRep.deleteById(id);
        return inscriptionRep.findAll();
    }
    public List<CandidatInscriptionDto> getCandidatsByDirecteur(Long directeurId) {
        List<InscriptionModel> inscriptions =inscriptionRep.getCandidatsByDirecteurId(directeurId);
        return inscriptions.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());

    }
}

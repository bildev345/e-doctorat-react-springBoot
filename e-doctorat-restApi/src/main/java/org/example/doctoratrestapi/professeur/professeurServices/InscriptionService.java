package org.example.doctoratrestapi.professeur.professeurServices;

import org.example.doctoratrestapi.mappers.inscription.InscriptionMapper;
import org.example.doctoratrestapi.inscription.InscriptionRepository;
import org.example.doctoratrestapi.dtos.inscription.CandidatInscriptionDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscriptionService {
    private final InscriptionRepository inscriptionRep;
    private final InscriptionMapper mapper;
    public InscriptionService(InscriptionRepository inscriptionRep, InscriptionMapper mapper){
        this.inscriptionRep = inscriptionRep;
        this.mapper = mapper;
    }
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
}

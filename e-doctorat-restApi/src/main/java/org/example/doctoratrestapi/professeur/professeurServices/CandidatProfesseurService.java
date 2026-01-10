package org.example.doctoratrestapi.professeur.professeurServices;

import org.example.doctoratrestapi.mappers.candidat.CandidatMapper;
import org.example.doctoratrestapi.models.CandidatModel;
import org.example.doctoratrestapi.candidat.CandidatRepository;
import org.example.doctoratrestapi.dtos.candidat.CandidatDTO;
import org.example.doctoratrestapi.exception.ResourceNotFoundException;
import org.example.doctoratrestapi.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CandidatProfesseurService {
    private final CandidatRepository candidatRep;
    private final CandidatMapper mapper;
    public CandidatProfesseurService(CandidatRepository candidatRep,  CandidatMapper mapper) {
        this.candidatRep = candidatRep;
        this.mapper = mapper;
    }

    // get ALL candidat by prof
    public List<CandidatDTO> selectCandidatsByProfesseur() {
        long userProfesseurId = SecurityUtils.currentUserId();
        List<CandidatModel> candidats = candidatRep.selectCandidatsByProfesseurId(userProfesseurId);
        if(candidats.isEmpty()){
            throw new ResourceNotFoundException("La liste des candidats est vide!!!");
        }
        return candidats.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
    //get Candidat by Id
    public CandidatDTO getCandidatById(long id){
        Optional<CandidatModel> candidatModel = Optional.ofNullable(candidatRep.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le candidat avec l'id " + id + " ne se trouve pas!!!!")));
        return  mapper.toDto(candidatModel.get());
    }



}

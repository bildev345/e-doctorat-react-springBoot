package org.example.doctoratrestapi.candidat;


import org.example.doctoratrestapi.mappers.candidat.CandidatMapper;
import org.example.doctoratrestapi.models.CandidatModel;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CandidatService {
    private List<CandidatModel> candidats;
    @Autowired
    private CandidatRepository candidatRep;

    @Autowired
    private CandidatMapper mapper;

//    public Optional<CandidatModel> getCandidatById(long id){
//        return candidatRep.findById(id);
//    }
//    public List<CandidatDTO> selectAllCandidats(){
//        List<CandidatModel> candidats = candidatRep.findAll();
//        return  candidats
//                .stream()
//                .map(mapper::toDto)
//                .collect(Collectors.toList());
//    }
    public CandidatModel addCandidat(CandidatModel candidatModel){
        return candidatRep.save(candidatModel);
        //this.candidats = getAll();
        //return this.candidats;
    }
    /*public List<CandidatModel> deleteCandidat(long id){
        candidatRep.deleteById(id);
        this.candidats = selectAllCandidats();
        return this.candidats;
    }*/
    public CandidatModel searchCandidatByName(String name){
        return candidatRep.searchCandidatByNomCandidatArabe(name);
    }
    /*public List<CandidatModel> updateCandidat(Integer id, CandidatModel candidatModel){
        candidatRep.save()
    }*/


//    public List<CandidatDTO> selectCandidatsByProfesseur() {
//        long userProfesseurId = SecurityUtils.currentUserId();
//        List<CandidatModel> candidats = candidatRep.selectCandidatsByProfesseurId(userProfesseurId);
//        return candidats.stream()
//                .map(mapper::toDto)
//                .collect(Collectors.toList());
//
//    }

    /*public List<CandidatDTO> selectCandidatsByDirecteurLabo() {
        long userDirecteurLaboId = SecurityUtils.currentUserId();
        List<CandidatModel> candidats = candidatRep.selectCandidatsByDirecteurLabo(userDirecteurLaboId);
        return candidats.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }*/
}

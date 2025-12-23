package org.example.doctoratrestapi.candidat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class CandidatService {
    private List<CandidatModel> candidats;
    @Autowired
    private CandidatRepository candidatRep;
    public CandidatService(CandidatRepository candidatRep){
        this.candidatRep = candidatRep;
        /*this.candidats = new ArrayList<>();
        CandidatModel d1 = new CandidatModel(1, "M1311110", "ja25521888", "el baroudi",
                "leila", "narjis", "نرجس", "F", "Meknes","مكناس", "Fès", LocalDate.of(2005, 05, 28),
                "non", "fes-meknes", "0611111111", "pathcv", "pathPhoto", 1, "célibataire",
                3, 1, 1);
        CandidatModel d2 = new CandidatModel(2, "M13dddd0", "dsdezez", "el arfaoui",
                "bilal", "ouled tayeb", "اولاد الطيب", "M", "Fès","فاس", "Fès", LocalDate.of(1996, 06, 05),
                "non", "fes-meknes", "0611111111", "pathcv1", "pathPhoto1", 3, "célibataire",
                3, 1, 0);
        candidats.addAll(Arrays.asList(d1, d2));*/
    }
    public Optional<CandidatModel> getCandidatById(long id){
        /*Optional optional = Optional.empty();
        for(CandidatModel candidat : candidats){
            if(candidat.getId() == id){
                optional = Optional.of(candidat);
                return optional;
            }
        }
        return optional;/
         */
        return candidatRep.findById(id);
    }
    public List<CandidatModel> getAll(){
        return candidatRep.findAll();
        //return candidats;
    }
    public CandidatModel addCandidat(CandidatModel candidatModel){
        return candidatRep.save(candidatModel);
        //this.candidats = getAll();
        //return this.candidats;
    }
    public List<CandidatModel> deleteCandidat(long id){
        candidatRep.deleteById(id);
        this.candidats = getAll();
        return this.candidats;
    }
    public CandidatModel searchCandidatByName(String name){
        return candidatRep.searchCandidatByNomCandidatArabe(name);
    }
    /*public List<CandidatModel> updateCandidat(Integer id, CandidatModel candidatModel){
        candidatRep.save()
    }*/

}

package org.example.doctoratrestapi.diplome;


import org.example.doctoratrestapi.candidat.CandidatModel;
import org.example.doctoratrestapi.candidat.CandidatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiplomeService {
    @Autowired
    private DiplomeRepository diplomeRep;
    @Autowired
    private CandidatRepository candidatRep;
    public DiplomeModel addDiplome(Long candidatId, DiplomeModel diplomeModel ){
        Optional< CandidatModel> candidat =candidatRep.findById(candidatId);
        if(candidat.isPresent()){
            diplomeModel.setCandidat(candidat.get()); //Si le candidat existe onassocie le diplôme à ce candidat
            return diplomeRep.save(diplomeModel);

        }
        throw new RuntimeException("Candidat non trouvé");
        //Si le candidat nexiste pas lance une exception avec un message derreur
    }

    public List<DiplomeModel> getDiplomesByCandidat(Long candidatId){
        return diplomeRep.findByCandidatId(candidatId);
    }
    public DiplomeModel getDiplomeById(Long diplomeId){
        return diplomeRep.findById(diplomeId).get();
    }
    public List<DiplomeModel> deleteDiplome(Long id) {
        if (!diplomeRep.existsById(id)) {
            throw new RuntimeException("Diplôme non trouvé " );
        }
        diplomeRep.deleteById(id);
        return diplomeRep.findAll();
    }
    public List<DiplomeModel> getAllDiplomes() {
        return diplomeRep.findAll();
    }
}

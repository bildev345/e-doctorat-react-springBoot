package org.example.doctoratrestapi.candidat;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidatRepository extends JpaRepository<CandidatModel, Long> {
    public CandidatModel searchCandidatByNomCandidatArabe(String nomAr);
    //public List <CandidatDTO> getCandidatModelsBy
}

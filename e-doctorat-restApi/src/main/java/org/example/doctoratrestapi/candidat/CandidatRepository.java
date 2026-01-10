package org.example.doctoratrestapi.candidat;

import org.example.doctoratrestapi.models.CandidatModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidatRepository extends JpaRepository<CandidatModel, Long> {
    public CandidatModel searchCandidatByNomCandidatArabe(String nomAr);

    @Query("select p.candidatModel from PostulerModel p join p.sujetModel s join s.professeur pr where pr.user.id = :userProfesseurId")
    List<CandidatModel> selectCandidatsByProfesseurId(@Param("userProfesseurId") long userProfesseurId);


}

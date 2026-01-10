package org.example.doctoratrestapi.postuler;

import org.example.doctoratrestapi.models.PostulerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostulerRepository extends JpaRepository<PostulerModel, Long> {
//doubt
    // nombre de postulations d’un candidat
    int countByCandidatModel_Id(Long candidatId);

    // candidats ayant postulé à un sujet
    List<PostulerModel> findBySujetModel_Id(Long sujetId);
}

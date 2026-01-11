package org.example.doctoratrestapi.inscription;

import org.example.doctoratrestapi.models.InscriptionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface InscriptionRepository extends JpaRepository<InscriptionModel, Long> {

    // Prof : inscriptions d’un sujet
    List<InscriptionModel> findInscriptionModelsBySujet_Id(Long sujetId);

    // Directeur de labo : toutes les inscriptions de son labo
    //List<InscriptionModel> findBySujet_Laboratoire_Id(Long laboId);

    //List<InscriptionModel> findByLaboratoireId(@Param("laboId") Long laboId);
//   List<InscriptionModel> findByCandidatId(Long candidatId);

    @Query("select i from InscriptionModel i join i.sujet s join s.professeur p " +
            "join p.laboratoire l where l.directeur.id= :directeurId")
    List<InscriptionModel> getCandidatsByDirecteurId(@Param("directeurId") Long directeurId);

    public boolean existsByCandidatId(Long candidatId);

    @Query("select ins from InscriptionModel ins join ins.sujet s join s.professeur p where p.laboratoire.id = :laboId")
    List<InscriptionModel> getInscriptionsByLaboId(@Param("laboId") long laboId);
}



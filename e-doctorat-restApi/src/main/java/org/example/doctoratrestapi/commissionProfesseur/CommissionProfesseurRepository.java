package org.example.doctoratrestapi.commissionProfesseur;

import org.example.doctoratrestapi.models.CommissionProfesseurModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommissionProfesseurRepository extends JpaRepository<CommissionProfesseurModel, Long> {

    @Query("select pm from CommissionProfesseurModel pm join pm.professeur p where p.id = :professeurId ")
    List<CommissionProfesseurModel> findByProfesseur_Id(@Param("professeurId") long professeurId);

    //List<CommissionProfesseurModel> findByCommission_Id(long commissionId);
}

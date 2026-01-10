package org.example.doctoratrestapi.commission;

import org.example.doctoratrestapi.models.CommissionModel;
import org.example.doctoratrestapi.models.CommissionProfesseurModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommissionRepository extends JpaRepository<CommissionModel, Long> {

    @Query("SELECT DISTINCT c FROM CommissionModel c " +
            "LEFT JOIN FETCH c.laboratoire " +
            "LEFT JOIN FETCH c.commissionProfesseurs cp " +
            "LEFT JOIN FETCH cp.professeur " +
            "WHERE cp.professeur.id = :professeurId")
    List<CommissionModel> findByProfesseurId(long professeurId);

}


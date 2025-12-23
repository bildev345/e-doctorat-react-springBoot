package org.example.doctoratrestapi.commissionProfesseur;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommissionProfesseurRepository extends JpaRepository<CommissionProfesseurModel, Long> {

    List<CommissionProfesseurModel> findByProfesseur_Id(Long professeurId);
}

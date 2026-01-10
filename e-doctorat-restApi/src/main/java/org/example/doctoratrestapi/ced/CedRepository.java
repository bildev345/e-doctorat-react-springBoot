package org.example.doctoratrestapi.ced;

import org.example.doctoratrestapi.models.CedModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CedRepository extends JpaRepository<CedModel, Long> {
}

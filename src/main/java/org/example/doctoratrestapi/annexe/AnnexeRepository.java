package org.example.doctoratrestapi.annexe;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnexeRepository extends JpaRepository<AnnexeModel,Long> {
    List<AnnexeModel> findByDiplomeId(Long diplomeId);
}

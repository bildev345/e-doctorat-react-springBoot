package org.example.doctoratrestapi.diplome;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiplomeRepository extends JpaRepository<DiplomeModel,Long> {
    List<DiplomeModel> findByCandidatId(long candidatId);
}

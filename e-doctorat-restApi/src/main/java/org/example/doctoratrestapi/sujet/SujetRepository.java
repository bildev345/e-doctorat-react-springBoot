package org.example.doctoratrestapi.sujet;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface SujetRepository extends JpaRepository<SujetModel, Long> {
      @Query("select s from SujetModel s join s.professeur p where p.laboratoire.id = :laboId")
      public List<SujetModel> getSujetsByLaboId(@Param("laboId") Long laboId);

    Collection<Object> findByProfesseurId(Long professeurId);

}
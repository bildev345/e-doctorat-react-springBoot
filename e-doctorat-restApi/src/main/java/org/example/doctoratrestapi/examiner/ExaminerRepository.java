package org.example.doctoratrestapi.examiner;

import org.example.doctoratrestapi.models.ExaminerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExaminerRepository extends JpaRepository<ExaminerModel, Long> {

    // récupérer les examinations par laboratoire :
    // via la commission (qui pointe vers laboratoire)
    List<ExaminerModel> findByCommission_Laboratoire_Id(Long laboId);
}

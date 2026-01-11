package org.example.doctoratrestapi.directeurLabo.directeurLaboServices;

import lombok.RequiredArgsConstructor;
import org.example.doctoratrestapi.dtos.examination.ExaminationDTO;
import org.example.doctoratrestapi.examiner.ExaminerRepository;
import org.example.doctoratrestapi.exception.ResourceNotFoundException;
import org.example.doctoratrestapi.mappers.examiner.ExaminerMapper;
import org.example.doctoratrestapi.models.ExaminerModel;
import org.example.doctoratrestapi.models.ProfesseurModel;
import org.example.doctoratrestapi.professeur.ProfesseurRepository;
import org.example.doctoratrestapi.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ViewExaminationsService {
    private final ExaminerRepository examinerRepository;
    private final ProfesseurRepository professeurRepository;
    private final ExaminerMapper examinerMapper;

    public List<ExaminationDTO> selectExaminationsByLabo(){
        long userId = SecurityUtils.currentUserId();
        Optional<ProfesseurModel> professeur = Optional.ofNullable(professeurRepository.findByUserId(userId).
                orElseThrow(() -> new ResourceNotFoundException("Le professeur n'existe pas")));

        long laboId = professeur.get().getLaboratoire().getId();
        List<ExaminerModel> examinations = examinerRepository.findExaminationsByLabo(laboId);
        return examinations.stream().map(examinerMapper::toDto).toList();
    }
}

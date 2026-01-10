package org.example.doctoratrestapi.professeur.professeurServices;

import org.example.doctoratrestapi.candidat.CandidatRepository;
import org.example.doctoratrestapi.commission.CommissionRepository;
import org.example.doctoratrestapi.examiner.ExaminerRepository;
import org.example.doctoratrestapi.mappers.examiner.ExaminerMapper;
import org.example.doctoratrestapi.models.CandidatModel;
import org.example.doctoratrestapi.models.CommissionModel;
import org.example.doctoratrestapi.models.ExaminerModel;
import org.example.doctoratrestapi.dtos.examination.ExaminationCreationDTO;
import org.example.doctoratrestapi.sujet.SujetModel;
import org.example.doctoratrestapi.sujet.SujetRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfExaminerService {
    ExaminerRepository  examinerRepository;
    CommissionRepository commissionRepository;
    CandidatRepository candidatRepository;
    SujetRepository sujetRepository;
    ExaminerMapper examinerMapper;
    public ProfExaminerService(ExaminerRepository examinerRepository,
                               CommissionRepository commissionRepository,
                               CandidatRepository candidatRepository,
                               SujetRepository sujetRepository,
                               ExaminerMapper examinerMapper) {
        this.examinerRepository = examinerRepository;
        this.commissionRepository = commissionRepository;
        this.candidatRepository = candidatRepository;
        this.sujetRepository = sujetRepository;
        this.examinerMapper = examinerMapper;
    }
    // le professeur notifier le candidat
    public void addExamination(ExaminationCreationDTO dto) {
        CandidatModel candidat = candidatRepository.findById(dto.getCandidatId())
                .orElseThrow(() -> new RuntimeException("Candidat non trouvé"));

        CommissionModel commission = commissionRepository.findById(dto.getCommissionId())
                .orElseThrow(() -> new RuntimeException("Commission non trouvée"));

        SujetModel sujet = sujetRepository.findById(dto.getSujetId())
                .orElseThrow(() -> new RuntimeException("Sujet non trouvé"));

        ExaminerModel examinerModel = examinerMapper.toEntity(dto);
        examinerModel.setCandidat(candidat);
        examinerModel.setCommission(commission);
        examinerModel.setSujet(sujet);

        examinerRepository.save(examinerModel);
    }
}

package org.example.doctoratrestapi.professeur.professeurServices;

import org.example.doctoratrestapi.candidat.CandidatRepository;
import org.example.doctoratrestapi.commission.CommissionRepository;
import org.example.doctoratrestapi.examiner.ExaminerRepository;
import org.example.doctoratrestapi.exception.ResourceNotFoundException;
import org.example.doctoratrestapi.mappers.examiner.ExaminerMapper;
import org.example.doctoratrestapi.models.CandidatModel;
import org.example.doctoratrestapi.models.CommissionModel;
import org.example.doctoratrestapi.models.ExaminerModel;
import org.example.doctoratrestapi.dtos.examination.ExaminationCreationDTO;
import org.example.doctoratrestapi.models.ProfesseurModel;
import org.example.doctoratrestapi.professeur.ProfesseurRepository;
import org.example.doctoratrestapi.sujet.SujetModel;
import org.example.doctoratrestapi.sujet.SujetRepository;
import org.example.doctoratrestapi.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExaminerService {
    private final CandidatRepository candidatRepo;
    private final CommissionRepository commissionRepo;
    private final SujetRepository sujetRepo;
    private final ExaminerRepository examinerRepo;
    private final ExaminerMapper examinerMapper;
    private final ProfesseurRepository profRepo;
    public ExaminerService(CandidatRepository candidatRepo,
                           CommissionRepository commissionRepo,
                           SujetRepository sujetRep,
                           ExaminerRepository examinerRepo,
                           ExaminerMapper examinerMapper,
                           ProfesseurRepository profRepo
    ){
        this.candidatRepo = candidatRepo;
        this.commissionRepo = commissionRepo;
        this.sujetRepo = sujetRep;
        this.examinerRepo = examinerRepo;
        this.examinerMapper = examinerMapper;
        this.profRepo = profRepo;
    }
    public void addExamination(ExaminationCreationDTO dto) {
        long userId = SecurityUtils.currentUserId();
        Optional<ProfesseurModel> prof = Optional.ofNullable(profRepo.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Le professeur est introuvable")));
        CandidatModel candidat = candidatRepo.findById(dto.getCandidatId())
                .orElseThrow(() -> new ResourceNotFoundException("Candidat non trouvé"));

        CommissionModel commission = commissionRepo.findById(dto.getCommissionId())
                .orElseThrow(() -> new ResourceNotFoundException("Commission non trouvée"));

        SujetModel sujet = sujetRepo.findById(dto.getSujetId())
                .orElseThrow(() -> new ResourceNotFoundException("Sujet non trouvé"));

        ExaminerModel examinerModel = examinerMapper.toEntity(dto);

        examinerModel.setCandidat(candidat);
        examinerModel.setCommission(commission);
        examinerModel.setSujet(sujet);

        examinerRepo.save(examinerModel);
    }
}

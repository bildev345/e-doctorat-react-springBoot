package org.example.doctoratrestapi.examiner;

import org.example.doctoratrestapi.models.CandidatModel;
import org.example.doctoratrestapi.candidat.CandidatRepository;
import org.example.doctoratrestapi.models.CommissionModel;
import org.example.doctoratrestapi.commission.CommissionRepository;
import org.example.doctoratrestapi.dtos.examination.ExaminationCreationDTO;
import org.example.doctoratrestapi.models.ExaminerModel;
import org.example.doctoratrestapi.sujet.SujetModel;
import org.example.doctoratrestapi.sujet.SujetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExaminerService {

    @Autowired
    private  ExaminerRepository examinerRepository;
    @Autowired
    private  CandidatRepository candidatRepository;
    @Autowired
    private  CommissionRepository commissionRepository;
    @Autowired
    private  SujetRepository sujetRepository;

    public void addExamination(ExaminerModel entity, ExaminationCreationDTO dto) {
        CandidatModel candidat = candidatRepository.findById(dto.getCandidatId())
                .orElseThrow(() -> new RuntimeException("Candidat non trouvé"));

        CommissionModel commission = commissionRepository.findById(dto.getCommissionId())
                .orElseThrow(() -> new RuntimeException("Commission non trouvée"));

        SujetModel sujet = sujetRepository.findById(dto.getSujetId())
                .orElseThrow(() -> new RuntimeException("Sujet non trouvé"));

        entity.setCandidat(candidat);
        entity.setCommission(commission);
        entity.setSujet(sujet);

        examinerRepository.save(entity);
    }

    public List<ExaminerModel> getAllByLaboratoire(Long laboId) {
        return examinerRepository.findByCommission_Laboratoire_Id(laboId);
    }
}

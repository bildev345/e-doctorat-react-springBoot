package org.example.doctoratrestapi.professeur;

import org.example.doctoratrestapi.dtos.commission.CommissionDTO;
import org.example.doctoratrestapi.dtos.candidat.CandidatDTO;
import org.example.doctoratrestapi.dtos.examination.ExaminationCreationDTO;
import org.example.doctoratrestapi.dtos.inscription.CandidatInscriptionDto;
import org.example.doctoratrestapi.professeur.professeurServices.*;
import org.example.doctoratrestapi.dtos.sujet.SujetDTO;
import org.example.doctoratrestapi.dtos.sujet.SujetDtoCreation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesseurServiceFacadeImp implements ProfesseurServiceFacade {
    private final CandidatProfesseurService candidatProfesseurService;
    private final CommissionProfService commissionProfService;
    private final ExaminerService examinerService;
    private final InscriptionService inscriptionService;
    //private final NotifierService notifierService;
    private final ProfExaminerService  profExaminerService;
    private final SujetService sujetService;

    public ProfesseurServiceFacadeImp(
            CandidatProfesseurService candidatProfesseurService,
            CommissionProfService commissionProfService,
            ExaminerService examinerService,
            InscriptionService inscriptionService,
            ProfExaminerService profExaminerService,
            SujetService sujetService
            ) {
        this.candidatProfesseurService = candidatProfesseurService;
        this.commissionProfService = commissionProfService;
        this.examinerService = examinerService;
        this.inscriptionService = inscriptionService;
        this.profExaminerService = profExaminerService;
        this.sujetService = sujetService;

    }
    public CandidatDTO getCandidatById(long id){
        CandidatDTO candidatModel = candidatProfesseurService.getCandidatById(id);
    }


    public List<CommissionDTO> getCommissionsByProfesseur(){
        return commissionProfService.getCommissionsByProfesseur();
    }

    public void addExamination(ExaminationCreationDTO dto){
        examinerService.addExamination(dto);
    }

    public List<CandidatInscriptionDto> getInscriptionBySujet(Long sujetId) {
        return inscriptionService.getInscriptionBySujet(sujetId);
    }
    public List<CandidatDTO> selectCandidatsByProfesseur() {
        return candidatProfesseurService.selectCandidatsByProfesseur();
    }


    public List<CandidatInscriptionDto> getAllInscriptions(){
        return inscriptionService.getAllInscriptions();
    }
    public CandidatInscriptionDto getInscriptionById(Long id){
        return inscriptionService.getInscriptionById(id);
    }

    /*public void createNotification(NotificationCreationDTO dto){
        notifierService.createNotification(dto);
    }*/

    public SujetDTO addSujet(SujetDtoCreation sujetDtoCreation){
        return sujetService.addSujet(sujetDtoCreation);
    }
    public void deleteSujet(Long professeurId, Long sujetId){
        sujetService.deleteSujet(sujetId, professeurId);
    }
    public List<SujetDTO> getSujetsByProfesseur(Long professeurId){
        return sujetService.getSujetsByProfesseur(professeurId);
    }
    public SujetDTO getSujetById(Long sujetId){
        return sujetService.getSujetById(sujetId);
    }
    public SujetDTO updateSujet(Long professeurId, Long sujetId, SujetDtoCreation sujetDtoCreation){
        return sujetService.updateSujet(sujetId, professeurId, sujetDtoCreation);
    }




}

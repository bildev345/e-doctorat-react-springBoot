package org.example.doctoratrestapi.professeur;

import org.example.doctoratrestapi.dtos.commission.CommissionDTO;
import org.example.doctoratrestapi.dtos.candidat.CandidatDTO;
import org.example.doctoratrestapi.dtos.examination.ExaminationCreationDTO;
import org.example.doctoratrestapi.dtos.inscription.CandidatInscriptionDto;
import org.example.doctoratrestapi.dtos.sujet.SujetDTO;
import org.example.doctoratrestapi.dtos.sujet.SujetDtoCreation;

import java.util.List;

public interface ProfesseurServiceFacade {
    public List<CandidatDTO> selectCandidatsByProfesseur();
    public CandidatDTO getCandidatById(long id);

    public List<CommissionDTO> getCommissionsByProfesseur();

    public void addExamination(ExaminationCreationDTO dto);

    public List<CandidatInscriptionDto> getInscriptionBySujet(Long sujetId);
    public List<CandidatInscriptionDto> getAllInscriptions();
    public CandidatInscriptionDto getInscriptionById(Long id);

    //public void createNotification(NotificationCreationDTO dto);

    public SujetDTO addSujet(SujetDtoCreation sujetDtoCreation);
    public void deleteSujet(Long sujetId);
    public List<SujetDTO> getSujetsByProfesseur(Long professeurId);
    public SujetDTO getSujetById(Long sujetId);
    public SujetDTO updateSujet(Long sujetId, SujetDtoCreation sujetDtoCreation);


}

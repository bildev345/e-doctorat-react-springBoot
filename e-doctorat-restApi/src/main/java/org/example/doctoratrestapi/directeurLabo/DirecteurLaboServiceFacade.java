package org.example.doctoratrestapi.directeurLabo;

import org.example.doctoratrestapi.dtos.candidat.CandidatDTO;
import org.example.doctoratrestapi.dtos.commission.CommissionCreationDto;
import org.example.doctoratrestapi.dtos.commission.CommissionDTO;
import org.example.doctoratrestapi.dtos.examination.ExaminationDTO;
import org.example.doctoratrestapi.dtos.inscription.CandidatInscriptionDto;
import org.example.doctoratrestapi.dtos.notification.NotificationCreationDTO;
import org.example.doctoratrestapi.dtos.sujet.SujetDTO;
import org.example.doctoratrestapi.dtos.sujet.SujetDtoCreation;

import java.util.List;

public interface DirecteurLaboServiceFacade {
    public List<CandidatDTO> getCandidatsByLabo();
    public List<SujetDTO> getSujetsByLabo();
    public void addSujet(SujetDtoCreation sujetDto);
    public List<ExaminationDTO> getResultatExaminations();
    public List<CandidatInscriptionDto> getCandidatsInscritsByLabo();
    public CommissionDTO addCommission(CommissionCreationDto dto);
    public void addNotifications(List<NotificationCreationDTO> notifications);
//    public void uploaderSujetsCsv();
//    public void telechargerPVGlobal();

}

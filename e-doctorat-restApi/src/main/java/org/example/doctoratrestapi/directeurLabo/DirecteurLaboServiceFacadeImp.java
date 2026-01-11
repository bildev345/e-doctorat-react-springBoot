package org.example.doctoratrestapi.directeurLabo;

import lombok.RequiredArgsConstructor;
import org.example.doctoratrestapi.directeurLabo.directeurLaboServices.*;
import org.example.doctoratrestapi.dtos.candidat.CandidatDTO;
import org.example.doctoratrestapi.dtos.commission.CommissionCreationDto;
import org.example.doctoratrestapi.dtos.commission.CommissionDTO;
import org.example.doctoratrestapi.dtos.examination.ExaminationDTO;
import org.example.doctoratrestapi.dtos.inscription.CandidatInscriptionDto;
import org.example.doctoratrestapi.dtos.notification.NotificationCreationDTO;
import org.example.doctoratrestapi.dtos.sujet.SujetDTO;
import org.example.doctoratrestapi.dtos.sujet.SujetDtoCreation;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class DirecteurLaboServiceFacadeImp implements DirecteurLaboServiceFacade{
    private final ViewCandidatsService viewCandidatsService;
    private final SujetManagementService sujetService;
    private final ViewExaminationsService viewExaminationsService;
    private final ViewCandidatsInscritsService viewCandidatsInscritsService;
    private final CreateCommissionService createCommissionService;

    public List<CandidatDTO> getCandidatsByLabo(){
        return viewCandidatsService.selectCandidatsByLabo();

    }
    public List<SujetDTO> getSujetsByLabo(){
       return sujetService.selectSujetsByLabo();
    }
    public void addSujet(SujetDtoCreation sujetDto){
       sujetService.addSujet(sujetDto);
    }
    public List<ExaminationDTO> getResultatExaminations(){
        return viewExaminationsService.selectExaminationsByLabo();
    }
    public List<CandidatInscriptionDto> getCandidatsInscritsByLabo(){
        return viewCandidatsInscritsService.selectCandidatsInscritsByLabo();
    }
    public CommissionDTO addCommission(CommissionCreationDto dto){
        return createCommissionService.addCommission(dto);
    }
    public void addNotifications(List<NotificationCreationDTO> notifications){

    }


//    public void uploaderSujetsCsv();
//    public void telechargerPVGlobal();

}

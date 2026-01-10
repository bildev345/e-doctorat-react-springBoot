package org.example.doctoratrestapi.professeur;

import org.example.doctoratrestapi.api.ApiResponse;
import org.example.doctoratrestapi.dtos.commission.CommissionDTO;
import org.example.doctoratrestapi.dtos.candidat.CandidatDTO;
import org.example.doctoratrestapi.dtos.examination.ExaminationCreationDTO;
import org.example.doctoratrestapi.dtos.inscription.CandidatInscriptionDto;
import org.example.doctoratrestapi.dtos.notification.NotificationCreationDTO;
import org.example.doctoratrestapi.sujet.dto.SujetDTO;
import org.example.doctoratrestapi.sujet.dto.SujetDtoCreation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/prof")
public class ProfesseurController {
    private final ProfesseurServiceFacade professeurServiceFacade;
    public ProfesseurController(ProfesseurServiceFacade professeurServiceFacade) {
        this.professeurServiceFacade = professeurServiceFacade;
    }

    @PreAuthorize("hasRole('ROLE_PROFESSOR')")
    @GetMapping("allCandidats")
    public ResponseEntity<ApiResponse<List<CandidatDTO>>> getCandidatsByProfesseur(){
        List<CandidatDTO> candidatDtos = professeurServiceFacade.selectCandidatsByProfesseur();
        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        null,
                        candidatDtos,
                        Instant.now()
                )
        );
    }

    @GetMapping("getCandidat/{id}")
    public ResponseEntity<ApiResponse<CandidatDTO>> getCandidatById(@PathVariable Long id){
        CandidatDTO candidatDto = professeurServiceFacade.getCandidatById(id);
        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        null,
                        candidatDto,
                        Instant.now()
                )
        );

    }

    @PreAuthorize("hasRole('ROLE_PROFESSOR')")
    @GetMapping("addSujet")
    public ResponseEntity<ApiResponse<Void>> addSujet(@RequestBody SujetDtoCreation sujetDtoCreation){
        professeurServiceFacade.addSujet(sujetDtoCreation);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "Sujet est ajouté avec succès",
                        null,
                        Instant.now()
                ));

    }

    @DeleteMapping("delete/{sujetId}")
    public ResponseEntity<ApiResponse<Void>> deleteSujet(@PathVariable Long sujetId){
        professeurServiceFacade.deleteSujet(sujetId);
        return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Le sujet à été supprimé avec succès",
                null,
                Instant.now()
        ));
    }

    @GetMapping("getSujets")
    public ResponseEntity<ApiResponse<List<SujetDTO>>> getSujetsByProfesseur(){
        List<SujetDTO> sujetDTOS = professeurServiceFacade.getSujetsByProfesseur();
        return ResponseEntity.ok(new ApiResponse<>(
                true,
                null,
                sujetDTOS,
                Instant.now()
        ));

    }

    @GetMapping("getSujet/{sujetId}")
    public ResponseEntity<ApiResponse<SujetDTO>> getSujetById(@PathVariable Long sujetId){
        SujetDTO sujetDto = professeurServiceFacade.getSujetById(sujetId);
        return ResponseEntity.ok(new ApiResponse<>(
                true,
                null,
                sujetDTO,
                Instant.now()
        ));
    }

    @PutMapping("sujet/update/{sujetId}")
    public ResponseEntity<SujetDTO> updateSujet(@PathVariable Long sujetId, @RequestBody SujetDtoCreation sujetDtoCreation){
        SujetDTO updatedSujet = professeurServiceFacade.updateSujet(sujetId, sujetDtoCreation);
        return ResponseEntity.ok(new ApiResponse<>(
                true,
                "le sujet à été mis à jour avec succès",
                updatedSujet,
                Instant.now()
        ));
    }
    public ResponseEntity<ApiResponse<List<CommissionDTO>>> getCommissionsByProfesseur(){
        List<CommissionDTO> commissionDTOs = professeurServiceFacade.getCommissionsByProfesseur();
        return ResponseEntity.ok(new ApiResponse<>(
                true,
                null,
                commissionDTOs,
                Instant.now()
        ));
    }

    @PreAuthorize("hasRole('ROLE_PROFESSOR')")
    public ResponseEntity<ApiResponse<Void>> addExamination(@RequestBody ExaminationCreationDTO dto){
        professeurServiceFacade.addExamination(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "L'examination à été ajoutée avec succès",
                        null,
                        Instant.now()
                ));

    }

    public ResponseEntity<ApiResponse<List<CandidatInscriptionDto>>> getInscriptionBySujet(@PathVariable Long sujetId){
        List<CandidatInscriptionDto> inscriptionDtos = professeurServiceFacade.getInscriptionBySujet(sujetId);
        return ResponseEntity.ok(new ApiResponse<>(
                true,
                null,
                inscriptionDtos,
                Instant.now()
        ));

    }
    public ResponseEntity<ApiResponse<List<CandidatInscriptionDto>>> getAllInscriptions(){
        List<CandidatInscriptionDto> allInscriptions = professeurServiceFacade.getAllInscriptions();
        return ResponseEntity.ok(new ApiResponse<>(
                true,
                null,
                allInscriptions,
                Instant.now()
        ));
    }
    public ResponseEntity<ApiResponse<CandidatInscriptionDto>> getInscriptionById(@PathVariable Long id){
        CandidatInscriptionDto inscriptionDto = professeurServiceFacade.getInscriptionById(id);
        return ResponseEntity.ok(new ApiResponse<>(
                true,
                null,
                inscriptionDto,
                Instant.now()
        ));
    }

    /*public ResponseEntity<ApiResponse<Void>> createNotification(@RequestBody NotificationCreationDTO dto){
        professeurServiceFacade.createNotification(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "Notification à été crée avec succès",
                        null,
                        Instant.now()
                ));

    }*/

}

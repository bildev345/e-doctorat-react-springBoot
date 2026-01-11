package org.example.doctoratrestapi.directeurLabo;

import org.example.doctoratrestapi.api.ApiResponse;
import org.example.doctoratrestapi.dtos.candidat.CandidatDTO;
import org.example.doctoratrestapi.dtos.commission.CommissionCreationDto;
import org.example.doctoratrestapi.dtos.examination.ExaminationDTO;
import org.example.doctoratrestapi.dtos.inscription.CandidatInscriptionDto;
import org.example.doctoratrestapi.dtos.sujet.SujetDTO;
import org.example.doctoratrestapi.dtos.sujet.SujetDtoCreation;
import org.example.doctoratrestapi.models.ExaminerModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/directeurLabo")
@PreAuthorize("hasRole('ROLE_DIRECTEUR_LABO')")
public class DirecteurLaboController {
    private final DirecteurLaboServiceFacade directeurLaboServiceFacade;
    public DirecteurLaboController(DirecteurLaboServiceFacade directeurLaboServiceFacade){
        this.directeurLaboServiceFacade = directeurLaboServiceFacade;
    }

    @GetMapping("candidats")
    public ResponseEntity<ApiResponse<List<CandidatDTO>>> getCandidatsByLabo(){
       List<CandidatDTO> candidats = directeurLaboServiceFacade.getCandidatsByLabo();
       return ResponseEntity.ok(new ApiResponse<>(
               true,
               null,
               candidats,
               Instant.now()
       ));
    }
    public ResponseEntity<ApiResponse<List<SujetDTO>>> getSujetsByLabo(){
        List<SujetDTO> sujets = directeurLaboServiceFacade.getSujetsByLabo();
        return ResponseEntity.ok(new ApiResponse<>(
                true,
                null,
                sujets,
                Instant.now()
        ));
    }
    public ResponseEntity<ApiResponse<Void>> addSujet(@RequestBody SujetDtoCreation sujetDto){
        directeurLaboServiceFacade.addSujet(sujetDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "le sujet à été ajouté avec succés",
                        null,
                        Instant.now()
                ));
    }
    public ResponseEntity<ApiResponse<Void>> addCommission(@RequestBody CommissionCreationDto dto){
        directeurLaboServiceFacade.addCommission(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "La commission à été crée avec succès",
                        null,
                        Instant.now()
                ));

    }
    @GetMapping("/resultats")
    public ResponseEntity<ApiResponse<List<ExaminationDTO>>> getResultatExaminations() {
        List<ExaminationDTO> resultats = directeurLaboServiceFacade.getResultatExaminations();
        return ResponseEntity.ok(new ApiResponse<>(
                true,
                null,
                resultats,
                Instant.now()
        ));
    }
    public ResponseEntity<ApiResponse<List<CandidatInscriptionDto>>> getCandidatsInscritsByLabo(){
        List<CandidatInscriptionDto> inscriptions = directeurLaboServiceFacade.getCandidatsInscritsByLabo();
        return ResponseEntity.ok(new ApiResponse<>(
                true,
                null,
                inscriptions,
                Instant.now()
        ));
    }

    // à implementer
    //public void telechargerPVGlobal();
    //public void uploaderSujetsCsv();

}

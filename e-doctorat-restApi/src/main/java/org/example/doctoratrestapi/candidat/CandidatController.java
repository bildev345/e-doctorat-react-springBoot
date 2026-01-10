package org.example.doctoratrestapi.candidat;

import org.example.doctoratrestapi.models.CandidatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "http://localhost:5476") pour indiquer le port d'application react
@RequestMapping("/api/candidats")
public class CandidatController {
    @Autowired
    private CandidatService candidatService;

//    @PreAuthorize("hasRole('ROLE_DIRECTEUR_POLE')")
//    @GetMapping("allCandidats")
//    public ResponseEntity<ApiResponse<List<CandidatDTO>>> getAllCandidats(){
//        List<CandidatDTO> candidats =  candidatService.selectAllCandidats();
//        return ResponseEntity
//                .status(HttpStatus.CREATED)
//                .body(new ApiResponse<List<CandidatDTO>>(
//                        true,
//                        null,
//                        candidats,
//                        Instant.now()
//                ));
//    }
    @PreAuthorize("hasRole('ROLE_CANDIDAT')")
    @PostMapping("candidat/add")
    public CandidatModel addCandidat(@RequestBody CandidatModel candidatModel){
        return candidatService.addCandidat(candidatModel);
    }


    @PreAuthorize("hasAnyRole('ROLE_PROFESSOR', 'ROLE_DIRECTEUR_LABO', 'ROLE_DIRECTEUR_CED', 'ROLE_DIRECTEUR_POLE')")
    @GetMapping("search")
    public CandidatModel searchCandidatByName(@RequestParam String nomAr){
        return candidatService.searchCandidatByName(nomAr);
    }

//    @PreAuthorize("hasRole('ROLE_PROFESSOR')")
//    @GetMapping("professeur/allCandidats")
//    public ResponseEntity<ApiResponse<List<CandidatDTO>>> getCandidatsByProfesseur(){
//         List<CandidatDTO> candidatDtos = candidatService.selectCandidatsByProfesseur();
//         return ResponseEntity.ok(
//                 new ApiResponse<>(
//                         true,
//                         null,
//                         candidatDtos,
//                         Instant.now()
//                 )
//         );
//    }
    /*@PreAuthorize("hasRole('ROLE_DIRECTEUR_DE_LABORATOIRE')")
    @GetMapping("directeurLabo/allCandidats")
    public ResponseEntity<ApiResponse<List<CandidatDTO>>> getCandidatByDirecteurLabo(){
        List<CandidatDTO> candidatDtos = candidatService.selectCandidatsByDirecteurLabo();
        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        null,
                        candidatDtos,
                        Instant.now()
                )
        );
    }*/


    /*
    @PutMapping("candidat/{id}/update")
    public List<CandidatModel> updateCandidat(@PathVariable Integer id, @RequestBody CandidatModel candidatModel){
        return candidatService.updateCandidat(id, candidatModel)
    }*/
}


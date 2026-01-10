package org.example.doctoratrestapi.diplome;


import jakarta.validation.Valid;
import org.example.doctoratrestapi.api.ApiResponse;
import org.example.doctoratrestapi.dtos.diplome.DiplomeCreationDto;
import org.example.doctoratrestapi.mappers.diplome.DiplomeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/diplomes")

public class DiplomeController {
    @Autowired
    private DiplomeService diplomeService;
    @Autowired
    private DiplomeMapper diplomeMapper;

    @PreAuthorize("hasRole('ROLE_CANDIDAT')")
    @PostMapping("/diplome/create")
    public ResponseEntity<ApiResponse<Void>> createDiplome(@Valid @RequestBody DiplomeCreationDto diplomeDto)
    {
       diplomeService.createDiplome(diplomeDto);
       return ResponseEntity
               .status(HttpStatus.CREATED)
               .body(new ApiResponse<>(
                       true,
                       "Diplome ajouté avec succès",
                       null,
                       Instant.now()
               ));

    }

    @PreAuthorize("hasAnyRole('ROLE_PROFESSOR', 'ROLE_DIRECTEUR_LABO', 'ROLE_DIRECTEUR_CED', 'ROLE_DIRECTEUR_POLE')")
    @GetMapping("/candidat/{candidatId}")
    public List<DiplomeModel> getDiplomesByCandidat(@PathVariable Long candidatId){
       return diplomeService.getDiplomesByCandidat(candidatId);
    }
    /*@GetMapping("/{id}")
    public DiplomeDto getDiplomeById(@PathVariable Long id) {
        return diplomeService.getDiplomeById(id);
    }*/
    /*@GetMapping("/all")
    public ResponseEntity<List<DiplomeDto>> getAllDiplomes() {
        List<DiplomeDto> diplomes =  diplomeService.getAllDiplomes();
        return ResponseEntity.ok(diplomes);
    }*/

    @PreAuthorize("hasRole('ROLE_CANDIDAT')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiplome(@PathVariable Long id) throws AccessDeniedException {
        diplomeService.deleteDiplome(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_CANDIDAT')")
    @PutMapping("/id")
    public ResponseEntity<ApiResponse<DiplomeCreationDto>> updateDiplome(@PathVariable Long id, @Valid @RequestBody DiplomeCreationDto diplomeDto) throws AccessDeniedException {
        diplomeService.updateDiplome(id, diplomeDto);
        return ResponseEntity.ok(new ApiResponse<>(
                        true,
                        "Le diplome est mis à jour avec succés",
                        diplomeDto,
                        Instant.now()
                ));
    }

}

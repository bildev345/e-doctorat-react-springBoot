package org.example.doctoratrestapi.annexe;


import org.example.doctoratrestapi.api.ApiResponse;
import org.example.doctoratrestapi.models.AnnexeModel;
import org.example.doctoratrestapi.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.time.Instant;

@RestController
@RequestMapping("/api/annexes")
public class AnnexeController {
    @Autowired
    private AnnexeService annexeService;
    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ROLE_CANDIDAT')")
    @PostMapping("/annexe/{diplomeId}/add")
    public ResponseEntity<ApiResponse<Void>> addAnnexe(@PathVariable Long diplomeId, @RequestBody AnnexeModel annexeModel) throws AccessDeniedException {
        annexeService.addAnnexe(diplomeId, annexeModel);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "Diplome ajouté avec succès",
                        null,
                        Instant.now()
                ));
    }
    /*@PreAuthorize("hasRole('ROLE_CANDIDAT')")
    @GetMapping("/diplome/{diplomeId}")
    public List<AnnexeModel> getAnnexesByDiplome(@PathVariable Long diplomeId) {
        return annexeService.getAnnexesByDiplome(diplomeId);
    }*/

    /*@PreAuthorize("hasRole('ROLE_CANDIDAT')")
    @GetMapping("/{id}")
    public AnnexeModel getAnnexeById(@PathVariable Long id) {
        return annexeService.getAnnexeById(id);
    }*/


    /*@GetMapping("/all")
    public List<AnnexeModel> getAllAnnexes() {
        return annexeService.getAllAnnexes();
    }*/

    @PreAuthorize("hasRole('ROLE_CANDIDAT')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteAnnexe(@PathVariable Long id) throws AccessDeniedException {
        annexeService.deleteAnnexe(id);
        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "L'annexe est supprimé avec succès",
                        null,
                        Instant.now()
                )
        );

    }
}

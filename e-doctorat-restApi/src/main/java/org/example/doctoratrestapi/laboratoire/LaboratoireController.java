package org.example.doctoratrestapi.laboratoire;

import org.example.doctoratrestapi.dtos.labo.LaboratoireDTO;
import org.example.doctoratrestapi.models.InscriptionModel;
import org.example.doctoratrestapi.models.SujetModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/laboratoires")
public class LaboratoireController {

    @Autowired
    private LaboratoireService laboratoireService;

    //Récupère tous les infos d 1 laboratoire
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public LaboratoireDTO getLaboratoire(@PathVariable Long id) {
        return laboratoireService.getLaboratoire(id);
    }

    //Liste TOUS les sujets du laboratoire
    @GetMapping("/{id}/sujets")
    @PreAuthorize("hasAnyRole('PROF', 'DIRECTEUR_LABO', 'DIRECTEUR_CED', 'DIRECTEUR_POLE')")
    public List<SujetModel> getSujets(@PathVariable Long id) {
        return laboratoireService.getSujetsByLaboratoire(id);
    }

    //Liste TOUS les candidats inscrits au labo
    @GetMapping("/{id}/candidats")
    @PreAuthorize("hasAnyRole('PROF', 'DIRECTEUR_LABO', 'DIRECTEUR_CED', 'DIRECTEUR_POLE')")
    public List<InscriptionModel> getCandidats(@PathVariable Long id) {
        return laboratoireService.getCandidatsByLaboratoire(id);
    }

    @PostMapping("/{id}/sujets/upload")
    @PreAuthorize("hasRole('DIRECTEUR_LABO')")

    public String uploadSujets(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        return laboratoireService.uploadSujetsCSV(id, file);
    }

    @GetMapping(value = "/{id}/pv-global", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @PreAuthorize("hasRole('DIRECTEUR_LABO')")
    public ResponseEntity<byte[]> generatePvGlobal(@PathVariable Long id) {
        byte[] csv = laboratoireService.generatePvGlobal(id);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=pv-global-" + id + ".csv")
                .body(csv);
    }
}

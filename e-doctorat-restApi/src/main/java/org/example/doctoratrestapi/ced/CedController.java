package org.example.doctoratrestapi.ced;

import org.example.doctoratrestapi.dtos.ced.CedDTO;
import org.example.doctoratrestapi.dtos.formationDoctorale.FormationDoctoraleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ceds")
public class CedController {

    @Autowired
    private CedService service;

    @GetMapping
    @PreAuthorize("hasRole('DIRECTEUR_POLE')")
    public List<CedDTO> getAllCeds() {
        return service.getAllCeds();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasanyRole('DIRECTEUR_CED', 'DIRECTEUR_POLE')")
    public CedDTO getCed(@PathVariable Long id) {
        return service.getCed(id);
    }

    @GetMapping("/{id}/rapport")
    @PreAuthorize("hasAnyRole('DIRECTEUR_CED', 'DIRECTEUR_POLE')")
    public String generateRapport(@PathVariable Long id) {
        return service.generateRapport(id);
    }

    @GetMapping("/{id}/formations")
    @PreAuthorize("hasanyRole('DIRECTEUR_CED', 'DIRECTEUR_POLE')")
    public List<FormationDoctoraleDTO> getFormations(@PathVariable Long id) {
        return service.getFormations(id);
    }
}

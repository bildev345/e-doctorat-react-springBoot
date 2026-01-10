package org.example.doctoratrestapi.formationdoctorale;

import org.example.doctoratrestapi.dtos.formationDoctorale.FormationDoctoraleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/formations")
public class FormationDoctoraleController {

    @Autowired private FormationDoctoraleService service;

    @GetMapping
    @PreAuthorize("hasRole('DIRECTEUR_POLE')")
    public List<FormationDoctoraleDTO> getAllFormations() {
        return service.getAllFormations();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('COORDONATEUR_FORMATION_DOCTORALE', 'DIRECTEUR_CED', 'DIRECTEUR_POLE')")
    public FormationDoctoraleDTO getFormation(@PathVariable Long id) {
        return service.getFormation(id);
    }

    @GetMapping("/ced/{cedId}")
    @PreAuthorize("hasanyRole('DIRECTEUR_CED', 'DIRECTEUR_POLE')")
    public List<FormationDoctoraleDTO> getByCed(@PathVariable Long cedId) {
        return service.getByCed(cedId);
    }

    @GetMapping("/etablissement/{etabId}")
    @PreAuthorize("hasanyRole('DIRECTEUR_CED', 'DIRECTEUR_POLE')")
    public List<FormationDoctoraleDTO> getByEtablissement(@PathVariable Long etabId) {
        return service.getByEtablissement(etabId);
    }
}

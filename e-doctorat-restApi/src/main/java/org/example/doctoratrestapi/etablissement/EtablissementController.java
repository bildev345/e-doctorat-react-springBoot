package org.example.doctoratrestapi.etablissement;

import org.example.doctoratrestapi.dtos.etablissement.EtablissementDTO;
import org.example.doctoratrestapi.dtos.formationDoctorale.FormationDoctoraleDTO;
import org.example.doctoratrestapi.dtos.labo.LaboratoireDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/etablissements")
public class EtablissementController {

    @Autowired private EtablissementService service;

    @GetMapping
    @PreAuthorize("hasRole('DIRECTEUR_POLE')")
    public List<EtablissementDTO> getAllEtablissements() {
        return service.getAllEtablissements();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('DIRECTEUR_POLE')")
    public EtablissementDTO getEtablissement(@PathVariable Long id) {
        return service.getEtablissement(id);
    }

    /*@GetMapping("/ced/{cedId}")
    @PreAuthorize("hasanyRole('DIRECTEUR_CED', 'DIRECTEUR_POLE')")
    public List<EtablissementDTO> getByCed(@PathVariable Long cedId) {
        return service.getByCed(cedId);
    }*/

    @GetMapping("/{id}/labos")
    @PreAuthorize("hasanyRole('DIRECTEUR_CED', 'DIRECTEUR_POLE')")
    public List<LaboratoireDTO> getLabos(@PathVariable Long id) {
        return service.getLabos(id);
    }

    @GetMapping("/{id}/formations")
    @PreAuthorize("hasanyRole('DIRECTEUR_CED', 'DIRECTEUR_POLE')")
    public List<FormationDoctoraleDTO> getFormations(@PathVariable Long id) {
        return service.getFormations(id);
    }
}

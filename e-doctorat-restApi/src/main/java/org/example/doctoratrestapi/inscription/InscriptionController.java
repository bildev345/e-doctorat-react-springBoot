package org.example.doctoratrestapi.inscription;

import lombok.AllArgsConstructor;

import org.example.doctoratrestapi.dtos.inscription.CandidatInscriptionCreationDto;
import org.example.doctoratrestapi.dtos.inscription.CandidatInscriptionDto;

import org.example.doctoratrestapi.models.InscriptionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@AllArgsConstructor
@RequestMapping("/api/inscriptions")
public class InscriptionController {
    @Autowired
    private InscriptionService inscriptionService;

    // le scolarité qui excerce le processus de l'ajout d'inscription
    @PostMapping("add")
    public CandidatInscriptionDto addInscription(@RequestBody CandidatInscriptionCreationDto dto) {
        return inscriptionService.addInscription(dto);
    }

    // Prof
    @GetMapping("sujet/{sujetId}")
    public List<CandidatInscriptionDto> getInscriptionBySujet(@PathVariable Long sujetId) {
            return inscriptionService.getInscriptionBySujet(sujetId);
    }

    @GetMapping("/all")
    public List<CandidatInscriptionDto> getAllInscriptions() {
        return inscriptionService.getAllInscriptions();
    }

    @GetMapping("/{id}")
    public CandidatInscriptionDto getInscriptionById(@PathVariable Long id) {
        return inscriptionService.getInscriptionById(id);
    }
    @DeleteMapping("/{id}")
    public List<InscriptionModel> deleteInscription(@PathVariable Long id) {
        return inscriptionService.deleteInscription(id);
    }
    // cette action est excercé par le directeur de laboratoire
    @GetMapping("{directeurId}/laboratoire/allCandidats")
    public List<CandidatInscriptionDto> getCandidatsByDirecteur(@PathVariable Long directeurId) {
        return inscriptionService.getCandidatsByDirecteur(directeurId);
    }
}


package org.example.doctoratrestapi.sujet;

import org.example.doctoratrestapi.sujet.dto.SujetDTO;
import org.example.doctoratrestapi.sujet.dto.SujetDtoCreation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/sujets")

public class SujetController {
    @Autowired
    private SujetService sujetService;

//    @PostMapping("sujet/{professeurId}/add")
//    public SujetDTO addSujet(@RequestBody SujetDtoCreation sujetDtoCreation, @PathVariable Long professeurId){
//        return sujetService.addSujet(sujetDtoCreation, professeurId);
//
//    }
    @GetMapping("{laboId}/getAllSujets")
    public List<SujetDTO> getAllSujetsByLaboId(Long laboId){
        return sujetService.getSujetsByLaboId(laboId);
    }

}

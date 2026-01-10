package org.example.doctoratrestapi.postuler;

import org.example.doctoratrestapi.dtos.postuler.PostulerCreationDTO;
import org.example.doctoratrestapi.dtos.postuler.PostulerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/postules")
public class PostulerController {
@Autowired
    private  PostulerService service;

    // candidat postule
    @PostMapping("/postuler")
    public void postuler(@RequestBody PostulerCreationDTO dto) {
        service.postuler(dto);
    }
    // prof consulte les candidats d’un sujet
    @GetMapping("/sujet/{sujetId}")
    public List<PostulerDTO> getCandidats(@PathVariable Long sujetId) {
        return service.getCandidatsBySujet(sujetId);
    }
}

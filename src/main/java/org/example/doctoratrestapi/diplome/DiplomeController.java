package org.example.doctoratrestapi.diplome;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diplomes")

public class DiplomeController {
    @Autowired
    private DiplomeService diplomeService;
   @PostMapping("/diplome/{candidatId}/add")
    public DiplomeModel addDiplome(@PathVariable Long candidatId ,
            @RequestBody DiplomeModel diplomeModel){
       return diplomeService.addDiplome(candidatId,diplomeModel);

   }

   @GetMapping("/candidat/{candidatId}")
    public List<DiplomeModel> getDiplomesByCandidat(@PathVariable Long candidatId){
       return diplomeService.getDiplomesByCandidat(candidatId);
   }
    @GetMapping("/{id}")
    public DiplomeModel getDiplomeById(@PathVariable Long id) {
        return diplomeService.getDiplomeById(id);
    }
    @GetMapping("/all")
    public List<DiplomeModel> getAllDiplomes() {
        return diplomeService.getAllDiplomes();
    }

    @DeleteMapping("/{id}")
    public List<DiplomeModel> deleteDiplome(@PathVariable Long id) {
        return diplomeService.deleteDiplome(id);
    }


}

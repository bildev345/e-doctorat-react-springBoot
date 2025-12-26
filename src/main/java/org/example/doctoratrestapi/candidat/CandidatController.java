package org.example.doctoratrestapi.candidat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@CrossOrigin(origins = "http://localhost:5476") pour indiquer le port d'application react
@RequestMapping("/api/candidats")
public class CandidatController {
    @Autowired
    private CandidatService candidatService;
    @GetMapping("candidat")
    public CandidatModel getCandidat(@RequestParam Long id){
        Optional candidatModel = candidatService.getCandidatById(id);
        if(candidatModel.isPresent()){
            return (CandidatModel) candidatModel.get();
        }
        else return null;
    }

    @GetMapping("getCandidat/{id}")
    public CandidatModel getCandidatById(@PathVariable Long id){
        Optional candidatModel = candidatService.getCandidatById(id);
        if(candidatModel.isPresent()){
            return (CandidatModel) candidatModel.get();
        }
        else return null;
    }

    @GetMapping("allCandidats")
    public List<CandidatModel> getAllCandidats(){
        return candidatService.getAll();
    }

    @PostMapping("candidat/add")
    public CandidatModel addCandidat(@RequestBody CandidatModel candidatModel){
        return candidatService.addCandidat(candidatModel);
    }

    @DeleteMapping("{id}") 
    public List<CandidatModel> deleteDoctorant(@PathVariable Long id){
        return candidatService.deleteCandidat(id);
    } 

    @GetMapping("search")
    public CandidatModel searchCandidatByName(@RequestParam String nomAr){
        return candidatService.searchCandidatByName(nomAr);
    }

    /*
    @PutMapping("candidat/{id}/update")
    public List<CandidatModel> updateCandidat(@PathVariable Integer id, @RequestBody CandidatModel candidatModel){
        return candidatService.updateCandidat(id, candidatModel)
    }*/
}


package org.example.doctoratrestapi.annexe;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/annexes")
public class AnnexeController {
    @Autowired
    private AnnexeService annexeService;
    @PostMapping("/annexe/{diplomeId}/add")
    public AnnexeModel addAnnexe(@PathVariable Long diplomeId, @RequestBody AnnexeModel annexeModel) {
        return annexeService.addAnnexe(diplomeId, annexeModel);
    }
    @GetMapping("/diplome/{diplomeId}")
    public List<AnnexeModel> getAnnexesByDiplome(@PathVariable Long diplomeId) {
        return annexeService.getAnnexesByDiplome(diplomeId);
    }
    @GetMapping("/{id}")
    public AnnexeModel getAnnexeById(@PathVariable Long id) {
        return annexeService.getAnnexeById(id);
    }

    @GetMapping("/all")
    public List<AnnexeModel> getAllAnnexes() {
        return annexeService.getAllAnnexes();
    }
    @DeleteMapping("/{id}")
    public List<AnnexeModel> deleteAnnexe(@PathVariable Long id) {
        return annexeService.deleteAnnexe(id);
    }
}

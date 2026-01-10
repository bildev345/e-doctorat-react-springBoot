package org.example.doctoratrestapi.annexe;

import org.example.doctoratrestapi.diplome.DiplomeModel;
import org.example.doctoratrestapi.diplome.DiplomeRepository;
import org.example.doctoratrestapi.exception.ResourceNotFoundException;
import org.example.doctoratrestapi.models.AnnexeModel;
import org.example.doctoratrestapi.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

@Service
public class AnnexeService {
    @Autowired
    private AnnexeRepository annexeRep;

    @Autowired
    private DiplomeRepository diplomeRep;
    public AnnexeModel addAnnexe(Long diplomeId, AnnexeModel annexeModel) throws AccessDeniedException {
        long candidatId = SecurityUtils.currentUserId();
        Optional<DiplomeModel> diplome = diplomeRep.findById(diplomeId);
        if (!diplome.isPresent()) {
            throw new ResourceNotFoundException("Diplôme non trouvé ");

        }
        if(diplome.get().getCandidat().getId() != candidatId){
            throw new AccessDeniedException("Vous n'avez pas le droit d'ajouter cette annexe");
        }
        annexeModel.setDiplome(diplome.get());
        return annexeRep.save(annexeModel);

    }

    public List<AnnexeModel> getAnnexesByDiplome(Long diplomeId) {
        return annexeRep.findByDiplomeId(diplomeId);
    }

    public AnnexeModel getAnnexeById(Long id) {
        return annexeRep.findById(id).orElseThrow(() ->
                new RuntimeException("Annexe non trouvée avec l'ID: " + id)
        );
    }
    public void deleteAnnexe(Long id) throws AccessDeniedException {
        long candidatId = SecurityUtils.currentUserId();
        if (!annexeRep.existsById(id)) {
            throw new ResourceNotFoundException("Annexe non trouvée avec l'ID: " + id);
        }

        if(annexeRep.getAnnexeByCandidatId(candidatId) == null){
            throw new AccessDeniedException("Vous n'avez pas le droit de supprimer l'annexe sous l'id: " + id);
        }
        annexeRep.deleteById(id);
    }
    /*public List<AnnexeModel> getAllAnnexes() {
        return annexeRep.findAll();
    }*/
}

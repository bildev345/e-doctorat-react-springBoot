package org.example.doctoratrestapi.annexe;

import org.example.doctoratrestapi.diplome.DiplomeModel;
import org.example.doctoratrestapi.diplome.DiplomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnnexeService {
    @Autowired
    private AnnexeRepository annexeRep;

    @Autowired
    private DiplomeRepository diplomeRep;
    public AnnexeModel addAnnexe(Long diplomeId, AnnexeModel annexeModel) {
        Optional<DiplomeModel> diplome = diplomeRep.findById(diplomeId);
        if (diplome.isPresent()) {
            annexeModel.setDiplome(diplome.get());
            return annexeRep.save(annexeModel);
        }
        throw new RuntimeException("Diplôme non trouvé ");
    }

    public List<AnnexeModel> getAnnexesByDiplome(Long diplomeId) {
        return annexeRep.findByDiplomeId(diplomeId);
    }

    public AnnexeModel getAnnexeById(Long id) {
        return annexeRep.findById(id).orElseThrow(() ->
                new RuntimeException("Annexe non trouvée avec l'ID: " + id)
        );
    }
    public List<AnnexeModel> deleteAnnexe(Long id) {
        if (!annexeRep.existsById(id)) {
            throw new RuntimeException("Annexe non trouvée avec l'ID: " + id);
        }
        annexeRep.deleteById(id);
        return annexeRep.findAll();
    }
    public List<AnnexeModel> getAllAnnexes() {
        return annexeRep.findAll();
    }
}

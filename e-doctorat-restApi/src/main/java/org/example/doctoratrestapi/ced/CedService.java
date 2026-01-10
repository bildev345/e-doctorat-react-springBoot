package org.example.doctoratrestapi.ced;

import org.example.doctoratrestapi.dtos.ced.CedDTO;
import org.example.doctoratrestapi.dtos.formationDoctorale.FormationDoctoraleDTO;
import org.example.doctoratrestapi.mappers.formationDoctorale.FormationDoctoraleMapper;
import org.example.doctoratrestapi.mappers.ced.CedMapper;
import org.example.doctoratrestapi.models.CedModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CedService {

    @Autowired private CedRepository repo;
    @Autowired private CedMapper mapper;
    private final FormationDoctoraleMapper formationMapper = new FormationDoctoraleMapper();

    //tous les CED (pour Dir Pole)
    public List<CedDTO> getAllCeds() {
        return mapper.toDTOs(repo.findAll());
    }

    //les infos CED détaillé
    public CedDTO getCed(Long id) {
        CedModel ced = repo.findById(id).orElseThrow(
                () -> new RuntimeException("CED non trouvé: " + id)
        );
        return mapper.toDTO(ced);
    }

    //Formations du CED (pour Dir CED)
    public List<FormationDoctoraleDTO> getFormations(Long cedId) {
        CedModel ced = repo.findById(cedId).orElseThrow(
                () -> new RuntimeException("CED non trouvé: " + cedId)
        );
        return formationMapper.toDTOs(ced.getFormations());
    }

    //Rapport inscription (pour Dir CED)
    public String generateRapport(Long cedId) {
        CedModel ced = repo.findById(cedId).orElseThrow(
                () -> new RuntimeException("CED non trouvé: " + cedId)
        );
        return "Rapport CED '" + ced.getTitre() + "' généré avec succès";
    }
}

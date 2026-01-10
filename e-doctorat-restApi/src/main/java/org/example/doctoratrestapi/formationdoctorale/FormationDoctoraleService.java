package org.example.doctoratrestapi.formationdoctorale;

import org.example.doctoratrestapi.dtos.formationDoctorale.FormationDoctoraleDTO;
import org.example.doctoratrestapi.mappers.formationDoctorale.FormationDoctoraleMapper;
import org.example.doctoratrestapi.models.CedModel;
import org.example.doctoratrestapi.ced.CedRepository;
import org.example.doctoratrestapi.models.EtablissementModel;
import org.example.doctoratrestapi.etablissement.EtablissementRepository;
import org.example.doctoratrestapi.models.FormationDoctoraleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormationDoctoraleService {

    @Autowired private FormationDoctoraleRepository repo;
    @Autowired private FormationDoctoraleMapper mapper;
    @Autowired private CedRepository cedRepo;
    @Autowired private EtablissementRepository etabRepo;

    // TOUS les formations (pour Dir CED)
    public List<FormationDoctoraleDTO> getAllFormations() {
        return mapper.toDTOs(repo.findAll());
    }

    //les infos formation détaillée
    public FormationDoctoraleDTO getFormation(Long id) {
        FormationDoctoraleModel formation = repo.findById(id).orElseThrow(
                () -> new RuntimeException("Formation non trouvée: " + id)
        );
        return mapper.toDTO(formation);
    }

    //Formations par CED (pour Dir CED)
//    public List<FormationDoctoraleDTO> getByCed(Long cedId) {
//        CedModel ced = cedRepo.findById(cedId).orElseThrow(
//                () -> new RuntimeException("CED non trouvé: " + cedId)
//        );
//        return mapper.toDTOs(repo.findByCed(ced));
//    }

    // Formations par Établissement (pour Dir Étab)
//    public List<FormationDoctoraleDTO> getByEtablissement(Long etabId) {
//        EtablissementModel etab = etabRepo.findById(etabId).orElseThrow(
//                () -> new RuntimeException("Etablissement non trouvé: " + etabId)
//        );
//        return mapper.toDTOs(repo.findByEtablissement(etab));
//    }
}

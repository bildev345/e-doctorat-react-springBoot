package org.example.doctoratrestapi.etablissement;
import org.example.doctoratrestapi.dtos.etablissement.EtablissementDTO;
import org.example.doctoratrestapi.dtos.formationDoctorale.FormationDoctoraleDTO;
import org.example.doctoratrestapi.formationdoctorale.FormationDoctoraleService;
import org.example.doctoratrestapi.dtos.labo.LaboratoireDTO;
import org.example.doctoratrestapi.mappers.etablissement.EtablissementMapper;
import org.example.doctoratrestapi.models.EtablissementModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtablissementService {

    @Autowired private EtablissementRepository repo;

    @Autowired private EtablissementMapper mapper;

    @Autowired private FormationDoctoraleService formationService;


    // TOUS les établissements (pour dir POl/CED)
    public List<EtablissementDTO> getAllEtablissements() {
        return mapper.toDTOs(repo.findAll());
    }

    // les infos d établissement détaillé
    public EtablissementDTO getEtablissement(Long id) {
        EtablissementModel etab = repo.findById(id).orElseThrow(
                () -> new RuntimeException("Etablissement non trouvé: " + id)
        );
        return mapper.toDTO(etab);
    }

    // Établissements par CED
    /*public List<EtablissementDTO> getByCed(Long cedId) {
        return getAllEtablissements();
    }*/

    //  Labos d'un établissement ( Dir CED voir labos)
    public List<LaboratoireDTO> getLabos(Long etabId) {
        EtablissementModel etab = repo.findById(etabId).orElseThrow(
                () -> new RuntimeException("Etablissement non trouvé: " + etabId)
        );
        return mapper.toLaboDTOs(etab.getLaboratoires());
    }
    //les formation d un labo
    public List<FormationDoctoraleDTO> getFormations(Long etabId) {
        return formationService.getByEtablissement(etabId);
    }
}


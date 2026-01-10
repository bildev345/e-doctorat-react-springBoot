package org.example.doctoratrestapi.mappers.laboratoire;

import org.example.doctoratrestapi.dtos.ced.CedDTO;
import org.example.doctoratrestapi.dtos.etablissement.EtablissementDTO;
import org.example.doctoratrestapi.dtos.labo.LaboratoireDTO;
import org.example.doctoratrestapi.mappers.ced.CedMapper;
import org.example.doctoratrestapi.mappers.etablissement.EtablissementMapper;
import org.example.doctoratrestapi.models.CedModel;
import org.example.doctoratrestapi.models.EtablissementModel;
import org.example.doctoratrestapi.models.LaboratoireModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LaboratoireMapper {
    private final CedMapper cedMapper;
    private final EtablissementMapper etabMapper;
    public LaboratoireMapper(CedMapper cedMapper, EtablissementMapper etabMapper){
        this.cedMapper = cedMapper;
        this.etabMapper = etabMapper;
    }
    // LaboratoireModel to LaboratoireDTO
    public LaboratoireDTO toDTO(LaboratoireModel labo) {
        Long laboId = labo.getId();
        String nom = labo.getNomLaboratoire();
        String description = labo.getDescription();
        CedDTO ced = cedMapper.toDTO(labo.getCed());
        EtablissementDTO etablissement = etabMapper.toDTO(labo.getEtablissement());

        return new LaboratoireDTO(laboId, nom, description, ced, etablissement);
    }

    public List<LaboratoireDTO> toDTOs(List<LaboratoireModel> labos) {
        return labos.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

//    public LaboratoireModel toLaboratoireModel(LaboratoireDTO laboratoireDTO) {
//        LaboratoireModel laboratoire = new LaboratoireModel();
//        laboratoire.setId(laboratoireDTO.laboId());
//        laboratoire.setNomLaboratoire(laboratoireDTO.nomLaboratoire());
//        laboratoire.setDescription(laboratoireDTO.description());
//        laboratoire.setCed(laboratoireDTO.cedDto());
////        String cedTitre,
////        String etablissementNom,
////        String directeurNom
//    }
}

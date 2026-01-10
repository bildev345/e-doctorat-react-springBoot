package org.example.doctoratrestapi.mappers.candidat;

import org.example.doctoratrestapi.models.CandidatModel;
import org.example.doctoratrestapi.mappers.diplome.DiplomeMapper;
import org.example.doctoratrestapi.diplome.DiplomeModel;
import org.example.doctoratrestapi.dtos.candidat.CandidatDTO;
import org.example.doctoratrestapi.dtos.diplome.DiplomeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CandidatMapper {
    @Autowired
    private DiplomeMapper mapper;

    public CandidatDTO toDto(CandidatModel candidat){
        String cne = candidat.getCne();
        String cin = candidat.getCin();
        String nomCandidatAr = candidat.getNomCandidatArabe();
        String prenomCandidatAr = candidat.getPrenomCandidatArabe();
        String adresse = candidat.getAdresse();
        List<DiplomeModel>  diplomes = candidat.getDiplomes();
        List<DiplomeDto> diplomesDto = diplomes.stream().map(mapper::toDto).collect(Collectors.toList());
        return new CandidatDTO(cne, cin, nomCandidatAr, prenomCandidatAr, adresse, diplomesDto);

    }
}

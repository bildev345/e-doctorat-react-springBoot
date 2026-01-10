package org.example.doctoratrestapi.dtos.candidat;

import org.example.doctoratrestapi.dtos.diplome.DiplomeDto;

import java.util.List;



public record CandidatDTO(
        String cne,
        String cin,
        String nomCandidatArabe,
        String prenomCandidatArabe,
        String adresse,
        List<DiplomeDto> diplomes
) {

};

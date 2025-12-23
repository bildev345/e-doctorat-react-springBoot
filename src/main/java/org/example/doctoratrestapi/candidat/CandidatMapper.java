package org.example.doctoratrestapi.candidat;

import org.example.doctoratrestapi.diplome.DiplomeModel;
import org.example.doctoratrestapi.candidat.CandidatDTO;

import java.util.List;

public class CandidatMapper {
    public CandidatDTO toDto(CandidatModel candidat){
        String cne = candidat.getCne();
        String cin = candidat.getCin();
        String nomCandidatAr = candidat.getNomCandidatArabe();
        String prenomCandidatAr = candidat.getPrenomCandidatArabe();
        String adresse = candidat.getAdresse();
        List<DiplomeModel>  diplomes = candidat.getDiplomes();
        return new CandidatDTO(cne, cin, nomCandidatAr, prenomCandidatAr, adresse, diplomes);

    }
}

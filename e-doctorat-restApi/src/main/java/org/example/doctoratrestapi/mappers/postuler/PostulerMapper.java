package org.example.doctoratrestapi.mappers.postuler;

import org.example.doctoratrestapi.models.CandidatModel;
import org.example.doctoratrestapi.models.PostulerModel;
import org.example.doctoratrestapi.dtos.postuler.PostulerCreationDTO;
import org.example.doctoratrestapi.dtos.postuler.PostulerDTO;
import org.example.doctoratrestapi.models.SujetModel;
import org.springframework.stereotype.Component;

@Component
public class PostulerMapper {

    public PostulerDTO toDto(PostulerModel postulerModel) {
        String pathFile =postulerModel.getPathFile();
        String cne =postulerModel.getCandidatModel().getCne();
        String cin =postulerModel.getCandidatModel().getCin();
        String nomCandidatArabe =postulerModel.getCandidatModel().getNomCandidatArabe();
        String prenomCandidatArabe =postulerModel.getCandidatModel().getPrenomCandidatArabe();
        String titre =postulerModel.getSujetModel().getTitre();
        String description =postulerModel.getSujetModel().getDescription();
        boolean publier =postulerModel.getSujetModel().isPublier();
        return new PostulerDTO(pathFile,cne,cin,nomCandidatArabe,prenomCandidatArabe,titre,description,publier);


    }
    public PostulerModel toModel(PostulerCreationDTO dtoCreation, CandidatModel candidatModel, SujetModel sujetModel) {
        PostulerModel postulerModel = new PostulerModel();
        postulerModel.setCandidatModel(candidatModel);
        postulerModel.setSujetModel(sujetModel);
        postulerModel.setPathFile(dtoCreation.getPathFile());
        return postulerModel;

    }
}

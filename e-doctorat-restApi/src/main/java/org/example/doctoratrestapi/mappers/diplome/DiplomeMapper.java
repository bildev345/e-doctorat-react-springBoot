package org.example.doctoratrestapi.mappers.diplome;


import org.example.doctoratrestapi.diplome.DiplomeModel;
import org.example.doctoratrestapi.models.AnnexeModel;
import org.example.doctoratrestapi.dtos.annexe.AnnexeCreationDto;
import org.example.doctoratrestapi.dtos.diplome.DiplomeCreationDto;
import org.example.doctoratrestapi.dtos.diplome.DiplomeDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DiplomeMapper {
    public DiplomeDto toDto(DiplomeModel diplomeModel) {
         String etablissement = diplomeModel.getEtablissement();
         String intitule = diplomeModel.getIntitule();
         String mention = diplomeModel.getMention();
         double moyGen = diplomeModel.getMoyenneGenerale();
         String ville = diplomeModel.getVille();
         List<AnnexeModel> annexes = diplomeModel.getAnnexes();

        return new DiplomeDto(etablissement, intitule, mention, moyGen, ville, annexes);
    }
    public DiplomeModel toDiplome(DiplomeCreationDto diplomeDto ){
          DiplomeModel diplome = new DiplomeModel();
          setEntityAttributes(diplome, diplomeDto);
          return diplome;

    }
    public void updateEntity(DiplomeModel diplome, DiplomeCreationDto diplomeDto){
        diplome.getAnnexes().clear();
        setEntityAttributes(diplome, diplomeDto);
    }
    private void setEntityAttributes(DiplomeModel diplome, DiplomeCreationDto diplomeDto){
        diplome.setIntitule(diplomeDto.intitule());
        diplome.setType(diplomeDto.type());
        diplome.setDateCommission(diplomeDto.dateCommission());
        diplome.setMention(diplomeDto.mention());
        diplome.setPays(diplomeDto.pays());
        diplome.setEtablissement(diplomeDto.etablissement());
        diplome.setSpecialite(diplomeDto.specialite());
        diplome.setVille(diplomeDto.ville());
        diplome.setProvince(diplomeDto.province());
        diplome.setMoyenneGenerale(diplomeDto.moyenneGenerale());
        for(AnnexeCreationDto annexeDto : diplomeDto.annexes()){
            AnnexeModel annexe = new AnnexeModel();
            annexe.setTypeAnnexe(annexeDto.typeAnnexe());
            annexe.setTitre(annexeDto.titre());
            annexe.setPathFile(annexeDto.pathFile());
            annexe.setDiplome(diplome);
            diplome.getAnnexes().add(annexe);
        }
    }
}

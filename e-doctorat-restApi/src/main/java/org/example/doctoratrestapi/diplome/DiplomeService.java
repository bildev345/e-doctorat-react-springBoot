package org.example.doctoratrestapi.diplome;


import org.example.doctoratrestapi.candidat.CandidatRepository;
import org.example.doctoratrestapi.dtos.diplome.DiplomeCreationDto;
import org.example.doctoratrestapi.dtos.diplome.DiplomeDto;
import org.example.doctoratrestapi.exception.ResourceNotFoundException;
import org.example.doctoratrestapi.mappers.diplome.DiplomeMapper;
import org.example.doctoratrestapi.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

@Service
public class DiplomeService {
    @Autowired
    private DiplomeRepository diplomeRep;
    @Autowired
    private CandidatRepository candidatRep;
    @Autowired
    DiplomeMapper mapper;

    @Transactional
    public void createDiplome(DiplomeCreationDto diplomeDto ){
        Long candidatId = SecurityUtils.currentUserId();
        DiplomeModel diplome = mapper.toDiplome(diplomeDto);
        diplome.getCandidat().setId(candidatId);
        diplomeRep.save(diplome);
    }

    public List<DiplomeModel> getDiplomesByCandidat(Long candidatId){
        return diplomeRep.findByCandidatId(candidatId);
    }

    public DiplomeDto getDiplomeById(Long diplomeId){
        Optional<DiplomeModel> diplome = diplomeRep.findById(diplomeId);
        return diplome.map(diplomeModel -> mapper.toDto(diplomeModel)).orElse(null);

    }

    public void deleteDiplome(Long id) throws AccessDeniedException {
        DiplomeModel findDiplome = getDiplomeOrThrow(id);
        long candidaId = SecurityUtils.currentUserId();
        if(findDiplome.getCandidat().getId() != candidaId){
            throw new AccessDeniedException("Vous n'avez pas le droit de modifier ce diplome!!");
        }
        diplomeRep.deleteById(id);
    }
    /*public List<DiplomeDto> getAllDiplomes() {
        return diplomeRep.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());

    }*/
    private DiplomeModel getDiplomeOrThrow(Long diplomeId){
        return diplomeRep.findById(diplomeId)
                .orElseThrow(() -> new ResourceNotFoundException("Le diplome est introuvable"));
    }
    @Transactional
    public void updateDiplome(long id, DiplomeCreationDto diplomeDto) throws AccessDeniedException{
        long candidatId = SecurityUtils.currentUserId();
        DiplomeModel findDiplome = getDiplomeOrThrow(id);
        if(findDiplome.getCandidat().getUser().getId() != candidatId){
            throw new AccessDeniedException("Vous n'avez pas le droit de modifier ce diplome!!");

        }
        mapper.updateEntity(findDiplome, diplomeDto);
        diplomeRep.save(findDiplome);
    }

}

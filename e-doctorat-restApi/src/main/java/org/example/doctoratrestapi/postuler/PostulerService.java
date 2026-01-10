package org.example.doctoratrestapi.postuler;

import lombok.AllArgsConstructor;
import org.example.doctoratrestapi.mappers.postuler.PostulerMapper;
import org.example.doctoratrestapi.models.CandidatModel;
import org.example.doctoratrestapi.candidat.CandidatRepository;
import org.example.doctoratrestapi.models.PostulerModel;
import org.example.doctoratrestapi.dtos.postuler.PostulerCreationDTO;
import org.example.doctoratrestapi.dtos.postuler.PostulerDTO;
import org.example.doctoratrestapi.sujet.SujetModel;
import org.example.doctoratrestapi.sujet.SujetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostulerService {

    private final SujetRepository sujetRepository;
    private final PostulerRepository postulerRepository;
    private final CandidatRepository candidatRepository;
    private final PostulerMapper mapper;

    // candidat postule (max 3 sujets)
    public void postuler(PostulerCreationDTO dto) {

        int nbPostulations =
                postulerRepository.countByCandidatModel_Id(dto.getCandidatId());

        if (nbPostulations >= 3) {
            throw new RuntimeException(
                    "Le candidat ne peut postuler qu'à 3 sujets maximum"
            );
        }

        CandidatModel candidat = candidatRepository
                .findById(dto.getCandidatId())
                .orElseThrow(() ->
                        new RuntimeException("Candidat introuvable")
                );

        SujetModel sujet = sujetRepository
                .findById(dto.getSujetId())
                .orElseThrow(() ->
                        new RuntimeException("Sujet introuvable")
                );

        PostulerModel postuler = new PostulerModel();
        postuler.setCandidatModel(candidat);
        postuler.setSujetModel(sujet);
        postuler.setPathFile(dto.getPathFile());

        postulerRepository.save(postuler);
    }

    /**
     * Le professeur consulte les candidats d’un sujet
     */
    public List<PostulerDTO> getCandidatsBySujet(Long sujetId) {
        return postulerRepository.findBySujetModel_Id(sujetId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}

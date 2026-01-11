package org.example.doctoratrestapi.directeurLabo.directeurLaboServices;

import lombok.RequiredArgsConstructor;
import org.example.doctoratrestapi.candidat.CandidatRepository;
import org.example.doctoratrestapi.dtos.candidat.CandidatDTO;
import org.example.doctoratrestapi.exception.ResourceNotFoundException;
import org.example.doctoratrestapi.mappers.candidat.CandidatMapper;
import org.example.doctoratrestapi.models.CandidatModel;
import org.example.doctoratrestapi.models.ProfesseurModel;
import org.example.doctoratrestapi.professeur.ProfesseurRepository;
import org.example.doctoratrestapi.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ViewCandidatsService {
    private final CandidatRepository candidatRepository;
    private final ProfesseurRepository professeurRepository;
    private final CandidatMapper candidatMapper;

    public List<CandidatDTO> selectCandidatsByLabo(){
        long userId = SecurityUtils.currentUserId();
        Optional<ProfesseurModel> professeur = Optional.ofNullable(professeurRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Le professeur n'existe pas")));
        List<CandidatModel> candidats = candidatRepository.selectCandidatsByDirecteurLaboId(professeur.get().getId());
        return candidats.stream().map(candidatMapper::toDto).toList();
    }
}

package org.example.doctoratrestapi.directeurLabo.directeurLaboServices;

import lombok.RequiredArgsConstructor;
import org.example.doctoratrestapi.dtos.inscription.CandidatInscriptionDto;
import org.example.doctoratrestapi.inscription.InscriptionRepository;
import org.example.doctoratrestapi.mappers.inscription.InscriptionMapper;
import org.example.doctoratrestapi.models.InscriptionModel;
import org.example.doctoratrestapi.models.ProfesseurModel;
import org.example.doctoratrestapi.professeur.ProfesseurRepository;
import org.example.doctoratrestapi.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ViewCandidatsInscritsService {
    private final ProfesseurRepository professeurRepo;
    private final InscriptionRepository inscriptionRepo;
    private final InscriptionMapper inscriptionMapper;

    public List<CandidatInscriptionDto> selectCandidatsInscritsByLabo(){
        long userID = SecurityUtils.currentUserId();
        Optional<ProfesseurModel> professeur = professeurRepo.findByUserId(userID);
        long laboID = professeur.get().getLaboratoire().getId();
        List<InscriptionModel> candidatsInscrits = inscriptionRepo.getInscriptionsByLaboId(laboID);
        return candidatsInscrits.stream().map(inscriptionMapper::toDto).toList();
    }
}

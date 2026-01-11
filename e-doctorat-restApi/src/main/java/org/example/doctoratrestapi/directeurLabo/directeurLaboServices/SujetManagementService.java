package org.example.doctoratrestapi.directeurLabo.directeurLaboServices;

import lombok.RequiredArgsConstructor;
import org.example.doctoratrestapi.dtos.sujet.SujetDTO;
import org.example.doctoratrestapi.dtos.sujet.SujetDtoCreation;
import org.example.doctoratrestapi.exception.ResourceNotFoundException;
import org.example.doctoratrestapi.formationdoctorale.FormationDoctoraleRepository;
import org.example.doctoratrestapi.mappers.SujetMapper;
import org.example.doctoratrestapi.models.FormationDoctoraleModel;
import org.example.doctoratrestapi.models.ProfesseurModel;
import org.example.doctoratrestapi.models.SujetModel;
import org.example.doctoratrestapi.professeur.ProfesseurRepository;
import org.example.doctoratrestapi.sujet.SujetRepository;
import org.example.doctoratrestapi.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SujetManagementService {
    private final ProfesseurRepository professeurRepository;
    private final FormationDoctoraleRepository formationRepo;
    private final SujetRepository sujetRepo;
    private final SujetMapper sujetMapper;

    public List<SujetDTO> selectSujetsByLabo(){
        long userId = SecurityUtils.currentUserId();
        Optional<ProfesseurModel> professeur = Optional.ofNullable(professeurRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Le professeur n'existe pas")));
        long laboId = professeur.get().getLaboratoire().getId();

        List<SujetModel> sujets = sujetRepo.getSujetsByLaboId(laboId);
        return sujets.stream().map(sujetMapper::toDTO).toList();
    }
    public void addSujet(SujetDtoCreation dto){
        long userId = SecurityUtils.currentUserId();
        Optional<ProfesseurModel> professeur = Optional.ofNullable(professeurRepository.findByUserId(userId))
                .orElseThrow(() -> new ResourceNotFoundException("Le professeur n'existe pas"));
        Optional<FormationDoctoraleModel> formation = Optional.of(formationRepo.findById(dto.getFormationDoctoralId()))
                .orElseThrow(() -> new ResourceNotFoundException("La formation doctorale n'exist pas"));
        SujetModel sujet = sujetMapper.toSujet(dto, formation.get(), professeur.get());

    }
}

package org.example.doctoratrestapi.directeurLabo.directeurLaboServices;

import lombok.RequiredArgsConstructor;
import org.example.doctoratrestapi.candidat.CandidatRepository;
import org.example.doctoratrestapi.commission.CommissionRepository;
import org.example.doctoratrestapi.exception.ResourceNotFoundException;
import org.example.doctoratrestapi.mappers.notification.NotificationMapper;
import org.example.doctoratrestapi.models.CandidatModel;
import org.example.doctoratrestapi.models.CommissionModel;
import org.example.doctoratrestapi.models.NotificationModel;
import org.example.doctoratrestapi.dtos.notification.NotificationCreationDTO;
import org.example.doctoratrestapi.notification.NotificationRepository;
import org.example.doctoratrestapi.models.SujetModel;
import org.example.doctoratrestapi.sujet.SujetRepository;
import org.example.doctoratrestapi.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class NotifierService {
    CandidatRepository candidatRepository;
    SujetRepository sujetRepository;
    CommissionRepository commissionRepository;
    NotificationMapper  notificationMapper;
    NotificationRepository  notificationRepository;

    public void createNotification(List<NotificationCreationDTO> dto) {
        long userId = SecurityUtils.currentUserId();
        // vérifier que le role de l'utilisateur est celui de ROLE_DIRECTEUR_LABO => à implémenter aprés
        Optional<CandidatModel> candidat = Optional.ofNullable(candidatRepository.findById(dto.getCandidatId())
                .orElseThrow(() -> new RuntimeException("Candidat non trouvé")));
        Optional<SujetModel> sujet = Optional.ofNullable(sujetRepository.findById(dto.getSujetId())
                .orElseThrow(() -> new ResourceNotFoundException("le sujet n'existe pas")));
        Optional<CommissionModel> commission = Optional.ofNullable(commissionRepository.findById(dto.getCommissionId())
                .orElseThrow(() -> new ResourceNotFoundException("la commission n'existe pas")));
        if(candidat.isPresent() && sujet.isPresent() && commission.isPresent()){
            NotificationModel notificationModel = notificationMapper.toEntity(dto, candidat.get(), commission.get(), sujet.get());
            notificationRepository.save(notificationModel);
        }


    }
}

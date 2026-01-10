package org.example.doctoratrestapi.notification;

import org.example.doctoratrestapi.models.CandidatModel;
import org.example.doctoratrestapi.candidat.CandidatRepository;
import org.example.doctoratrestapi.models.NotificationModel;
import org.example.doctoratrestapi.dtos.notification.NotificationCreationDTO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final CandidatRepository candidatRepository;

    public NotificationService(NotificationRepository notificationRepository,
                               CandidatRepository candidatRepository) {
        this.notificationRepository = notificationRepository;
        this.candidatRepository = candidatRepository;
    }

    public void createNotification(NotificationModel entity, NotificationCreationDTO dto) {
        CandidatModel candidat = candidatRepository.findById(dto.getCandidatId())
                .orElseThrow(() -> new RuntimeException("Candidat non trouvé"));

        entity.setCandidat(candidat);

        notificationRepository.save(entity);
    }

    public List<NotificationModel> getNotificationsByCandidatId(Long candidatId) {
        return notificationRepository.findByCandidatId(candidatId);
    }
}


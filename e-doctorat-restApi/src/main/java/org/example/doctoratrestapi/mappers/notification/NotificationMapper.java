package org.example.doctoratrestapi.mappers.notification;

import org.example.doctoratrestapi.models.NotificationModel;
import org.example.doctoratrestapi.dtos.notification.NotificationCreationDTO;
import org.example.doctoratrestapi.dtos.notification.NotificationDTO;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    public NotificationDTO toDto(NotificationModel entity) {
        if (entity == null) return null;

        Long commissionId = entity.getCommission() != null
                ? entity.getCommission().getId()
                : null;

        Long sujetId = entity.getSujet() != null
                ? entity.getSujet().getId()
                : null;

        return new NotificationDTO(
                entity.getId(),
                entity.getType(),
                commissionId,
                sujetId
        );
    }

    public NotificationModel toEntity(NotificationCreationDTO dto) {
        if (dto == null) return null;

        NotificationModel entity = new NotificationModel();
        entity.setType(dto.getType());
        entity.getCandidat().setId(dto.getCandidatId());
        entity.getSujet().setId(dto.getSujetId());
        entity.getCommission().setId(dto.getCommissionId());
        return entity;
    }
}

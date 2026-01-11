package org.example.doctoratrestapi.dtos.notification;

import org.example.doctoratrestapi.dtos.sujet.SujetCandidatsDto;
import org.example.doctoratrestapi.utils.NotificationType;

import java.util.List;

public record NotificationBulkCreationDto(
        Long commissionId,
        NotificationType type,
        List<SujetCandidatsDto> sujetsCandidats
) {
    public NotificationBulkCreationDto{
        if(sujetsCandidats == null || sujetsCandidats.isEmpty()){
            throw new IllegalArgumentException("Au moins un sujet avec candidats doit etre sélectionné");
        }
    }
}

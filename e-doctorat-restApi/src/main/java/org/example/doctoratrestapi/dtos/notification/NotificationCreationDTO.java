package org.example.doctoratrestapi.dtos.notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class NotificationCreationDTO {
    // résultat ou entretien
    private String type;
    private Long candidatId;
    private long sujetId;
    private long commissionId;

}

package org.example.doctoratrestapi.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class NotificationDTO {
    private Long id;
    private String type;
    private Long commissionId;
    private Long sujetId;

}

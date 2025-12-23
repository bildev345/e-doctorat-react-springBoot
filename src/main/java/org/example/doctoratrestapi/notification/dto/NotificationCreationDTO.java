package org.example.doctoratrestapi.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class NotificationCreationDTO {
    private Long candidatId;
    private String type;


    }

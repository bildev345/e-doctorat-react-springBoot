package org.example.doctoratrestapi.postuler.dto;

import lombok.Data;

@Data
public class PostulerCreationDTO {
    private Long candidatId;
    private Long sujetId;
    private String pathFile;
}

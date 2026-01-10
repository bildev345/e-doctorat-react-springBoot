package org.example.doctoratrestapi.mappers.examiner;

import org.example.doctoratrestapi.dtos.examination.ExaminationCreationDTO;
import org.example.doctoratrestapi.dtos.examination.ExaminationDTO;
import org.example.doctoratrestapi.models.ExaminerModel;
import org.springframework.stereotype.Component;

@Component
public class ExaminerMapper {

    // Entity -> DTO (pour GET)
    public ExaminationDTO toDto(ExaminerModel entity) {
        Long candidatId = entity.getCandidat() != null ? entity.getCandidat().getId() : null;
        Long commissionId = entity.getCommission() != null ? entity.getCommission().getId() : null;
        Long sujetId = entity.getSujet() != null ? entity.getSujet().getId() : null;

        return new ExaminationDTO(
                entity.getId(),
                entity.getDecision(),
                entity.getNoteDossier(),
                entity.getNoteEntretien(),
                entity.isPublier(),
                entity.isValider(),
                candidatId,
                commissionId,
                sujetId
        );
    }

    // DTO création -> Entity (sans relations, gérées dans le service)
    public ExaminerModel toEntity(ExaminationCreationDTO dto) {
        ExaminerModel entity = new ExaminerModel();
        entity.setDecision(dto.getDecision());
        entity.setNoteDossier(dto.getNoteDossier());
        entity.setNoteEntretien(dto.getNoteEntretien());
        entity.setValider(dto.isValider());
        entity.getCandidat().setId(dto.getCandidatId());
        entity.getCommission().setId(dto.getCommissionId());
        entity.getSujet().setId(dto.getSujetId());
        return entity;
    }
}

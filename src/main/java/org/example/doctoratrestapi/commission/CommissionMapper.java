package org.example.doctoratrestapi.commission;

import org.example.doctoratrestapi.candidat.CandidatDTO;
import org.example.doctoratrestapi.laboratoire.LaboratoireModel;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component

public class CommissionMapper {

    public CommissionDTO toDto(CommissionModel commission) {
        return new CommissionDTO(
                commission.getDateCommission(),
                commission.getLieu(),
                commission.getHeure(),
                commission.getLaboratoire().getNomLaboratoire()

        );
    }
}

package org.example.doctoratrestapi.commission;

import jakarta.persistence.*;
import lombok.*;
import org.example.doctoratrestapi.commissionProfesseur.CommissionProfesseurModel;
import org.example.doctoratrestapi.examiner.ExaminerModel;
import org.example.doctoratrestapi.laboratoire.LaboratoireModel;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommissionDTO {
    private LocalDate dateCommission;
    private String lieu;
    private Time heure;
    private String laboratoire;
}

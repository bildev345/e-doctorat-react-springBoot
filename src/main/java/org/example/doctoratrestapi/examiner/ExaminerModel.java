package org.example.doctoratrestapi.examiner;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.doctoratrestapi.candidat.CandidatModel;
import org.example.doctoratrestapi.commission.CommissionModel;
import org.example.doctoratrestapi.sujet.SujetModel;

@Entity
@Table(name = "examiner")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ExaminerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String decision;
    private  float noteDossier;
    private int noteEntretien;
    private boolean publier;
    @ManyToOne
    @JoinColumn(name = "commission_id")
    private CommissionModel commission;

    @ManyToOne
    @JoinColumn(name = "sujet_id")
    private SujetModel sujet;

    @ManyToOne
    @JoinColumn(name = "candidat_id")
    private CandidatModel candidat;

    private boolean valider;

}

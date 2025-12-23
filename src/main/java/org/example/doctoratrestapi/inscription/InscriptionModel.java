package org.example.doctoratrestapi.inscription;


import jakarta.persistence.*;
import lombok.*;
import org.example.doctoratrestapi.candidat.CandidatModel;
import org.example.doctoratrestapi.sujet.SujetModel;

import java.time.LocalDate;

@Entity
@Table(name="inscriptions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class InscriptionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateDeposerDossier;
    private String remarque;
    private Boolean valider;

    @OneToOne
    @JoinColumn(name = "candidat_id")
    private CandidatModel candidat;

    @ManyToOne
    @JoinColumn(name = "sujet_id")
    private SujetModel sujet;

}

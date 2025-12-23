package org.example.doctoratrestapi.laboratoire;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.doctoratrestapi.ced.CedModel;
import org.example.doctoratrestapi.etablissement.EtablissementModel;
import org.example.doctoratrestapi.professeur.ProfesseurModel;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="laboratoires")
public class LaboratoireModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomLaboratoire;
    private String description;
    private String pathImage;
    private String initial;
    @ManyToOne
    @JoinColumn(name = "ced_id")
    private CedModel ced;

    @OneToOne
    @JoinColumn(name="directeur_id")
    private ProfesseurModel directeur;

    @ManyToOne
    @JoinColumn(name="etablissement_id")
    private EtablissementModel etablissement;

}
package org.example.doctoratrestapi.ced;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.doctoratrestapi.formationdoctorale.FormationDoctoraleModel;
import org.example.doctoratrestapi.professeur.ProfesseurModel;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ceds")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CedModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String description;
    private String pathImage;
    private String initiale;
    private String titre;

    @OneToOne
    @JoinColumn(name="directeur_id")
    private ProfesseurModel directeur;
    @OneToMany(mappedBy = "ced")
    private List<FormationDoctoraleModel> formations = new ArrayList<>();
}

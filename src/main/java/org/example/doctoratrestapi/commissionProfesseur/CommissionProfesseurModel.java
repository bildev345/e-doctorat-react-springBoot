package org.example.doctoratrestapi.commissionProfesseur;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.doctoratrestapi.commission.CommissionModel;
import org.example.doctoratrestapi.professeur.ProfesseurModel;

@Entity
@Table(name="commission_professeurs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommissionProfesseurModel {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id")
   private long id;
   @ManyToOne
   @JoinColumn(name="commission_id")
   private CommissionModel commission;
   @ManyToOne
   @JoinColumn(name="professeur_id")
   private ProfesseurModel professeur;
}

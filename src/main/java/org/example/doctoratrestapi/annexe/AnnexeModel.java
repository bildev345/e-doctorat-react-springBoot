package org.example.doctoratrestapi.annexe;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.doctoratrestapi.diplome.DiplomeModel;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="annexes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnnexeModel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String typeAnnexe;
    private String titre;
    private String pathFile;
    @ManyToOne
    @JoinColumn(name = "diplome_id")
    private DiplomeModel diplome;

}

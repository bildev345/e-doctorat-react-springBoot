package org.example.doctoratrestapi.mappers.commission;

import org.example.doctoratrestapi.dtos.commission.CommissionCreationDto;
import org.example.doctoratrestapi.dtos.labo.LaboratoireDTO;
import org.example.doctoratrestapi.dtos.professeur.ProfesseurDto;
import org.example.doctoratrestapi.mappers.laboratoire.LaboratoireMapper;
import org.example.doctoratrestapi.mappers.professeur.ProfesseurMapper;
import org.example.doctoratrestapi.models.CommissionModel;
import org.example.doctoratrestapi.dtos.commission.CommissionDTO;
import org.example.doctoratrestapi.models.CommissionProfesseurModel;
import org.example.doctoratrestapi.models.LaboratoireModel;
import org.example.doctoratrestapi.models.ProfesseurModel;
import org.example.doctoratrestapi.professeur.ProfesseurRepository;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component

public class CommissionMapper {
    private final ProfesseurMapper profMapper;
    private final LaboratoireMapper laboMapper;
    public CommissionMapper(ProfesseurMapper profMapper, LaboratoireMapper laboMapper){
        this.profMapper = profMapper;
        this.laboMapper = laboMapper;
    }
    public CommissionDTO toDto(CommissionModel commission) {
        if(commission == null){
            return null;
        }
        long commissionId = commission.getId();
        LocalDate dateCommission = commission.getDateCommission();
        String lieu = commission.getLieu();
        Time heure = commission.getHeure();
        long laboId = commission.getLaboratoire().getId();
        //à revoir
        LaboratoireDTO laboratoire = laboMapper.toDTO(commission.getLaboratoire());

        List<ProfesseurDto> professeurDtos = commission.getCommissionProfesseurs()
                .stream()
                .map(cp -> {
                    ProfesseurModel p = cp.getProfesseur();
                    return profMapper.toDto(p);
                })
                .toList();


        return new CommissionDTO(
                commissionId,
                dateCommission,
                lieu,
                heure,
                laboId,
                laboratoire,
                professeurDtos
        );
    }
    public List<CommissionDTO> toDtoList(List<CommissionModel> commissions) {
        return commissions.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
    public CommissionModel toCommissionModel(CommissionCreationDto dto, List<ProfesseurModel> professeurs, LaboratoireModel laboratoire) {
        if (dto == null) {
            return null;
        }
        CommissionModel commission = new CommissionModel();
        commission.setDateCommission(dto.dateCommission());
        commission.setLieu(dto.lieu());
        commission.setHeure(dto.heure());
        commission.setLaboratoire(laboratoire);
        List<CommissionProfesseurModel> commissionProfessors = professeurs.stream()
                .map(professeur -> {
                    CommissionProfesseurModel cp = new CommissionProfesseurModel();
                    cp.setCommission(commission);
                    cp.setProfesseur(professeur);
                    return cp;
                }).toList();
        commission.setCommissionProfesseurs(commissionProfessors);
        return commission;

    }
}

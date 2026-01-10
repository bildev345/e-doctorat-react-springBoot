package org.example.doctoratrestapi.professeur.professeurServices;

import org.example.doctoratrestapi.commission.CommissionRepository;
import org.example.doctoratrestapi.dtos.commission.CommissionDTO;
import org.example.doctoratrestapi.mappers.commission.CommissionMapper;
import org.example.doctoratrestapi.models.CommissionModel;
import org.example.doctoratrestapi.models.CommissionProfesseurModel;
import org.example.doctoratrestapi.exception.ResourceNotFoundException;
import org.example.doctoratrestapi.professeur.ProfesseurRepository;
import org.example.doctoratrestapi.utils.SecurityUtils;
import org.springframework.stereotype.Service;
import org.example.doctoratrestapi.models.ProfesseurModel;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommissionProfService {
    private final CommissionMapper commissionMappper;
    private final CommissionRepository commissionRepository;
    private final ProfesseurRepository profRepository;
    public CommissionProfService(CommissionRepository commissionRepository, ProfesseurRepository profRep, CommissionMapper commissionMappper){
        this.commissionRepository = commissionRepository;
        this.profRepository = profRep;
        this.commissionMappper = commissionMappper;
    }
    public List<CommissionDTO> getCommissionsByProfesseur() {
        long userProfId = SecurityUtils.currentUserId();
        Optional<ProfesseurModel> prof = Optional.ofNullable(profRepository.findByUserId(userProfId)
                .orElseThrow(() -> new ResourceNotFoundException("Il n'existe pas un prof associé avec cet ID")));
        List<CommissionModel> profCommissions = commissionRepository.findByProfesseurId(prof.get().getId());
        if(profCommissions.isEmpty()){
            throw new ResourceNotFoundException("Vous n'avez pas de commisssions");
        }
        return profCommissions
                .stream()
                .map(commissionMappper::toDto)
                .collect(Collectors.toList());
    }
}

package org.example.doctoratrestapi.commissionProfesseur;

import lombok.AllArgsConstructor;
import org.example.doctoratrestapi.commission.CommissionDTO;
import org.example.doctoratrestapi.commission.CommissionMapper;
import org.example.doctoratrestapi.commission.CommissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class CommissionProfesseurService {
@Autowired
    private  CommissionProfesseurRepository commissionProfRepo;
@Autowired
    private  CommissionMapper commissionMapper;

    public List<CommissionDTO> getAllCommissions(Long professeurId) {

        return commissionProfRepo
                .findByProfesseur_Id(professeurId)
                .stream()
                .map(CommissionProfesseurModel::getCommission)
                .distinct()
                .map(commissionMapper::toDto)
                .toList();
    }
}



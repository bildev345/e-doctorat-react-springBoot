package org.example.doctoratrestapi.commissionProfesseur;

import lombok.AllArgsConstructor;
import org.example.doctoratrestapi.dtos.commission.CommissionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/commissionProfesseurs")
@AllArgsConstructor
public class CommissionProfesseurController {
@Autowired
    private CommissionProfesseurService service;

    @GetMapping("/{professeurId}/getAllCommissions")
    public List<CommissionDTO> getAllCommissions(@PathVariable Long professeurId) {
        return service.getAllCommissions(professeurId);
    }
}

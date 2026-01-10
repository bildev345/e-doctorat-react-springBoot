package org.example.doctoratrestapi.examiner;

import org.example.doctoratrestapi.dtos.examination.ExaminationCreationDTO;
import org.example.doctoratrestapi.dtos.examination.ExaminationDTO;
import org.example.doctoratrestapi.mappers.examiner.ExaminerMapper;
import org.example.doctoratrestapi.models.ExaminerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/examinations")
public class ExaminerController {

    @Autowired
    private ExaminerService examinerService;
    @Autowired
    private ExaminerMapper examinerMapper;

    // POST: api/examinations/examiner/add
    @PostMapping("/examiner/add")
    public ResponseEntity<Void> addExamination(@RequestBody ExaminationCreationDTO dto) {
        ExaminerModel entity = examinerMapper.toEntity(dto);
        examinerService.addExamination(entity, dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // GET: api/examinations/{laboId}/getAllExaminations
    @GetMapping("/{laboId}/getAllExaminations")
    public ResponseEntity<List<ExaminationDTO>> getAllByLaboratoire(@PathVariable Long laboId) {
        List<ExaminerModel> entities = examinerService.getAllByLaboratoire(laboId);
        List<ExaminationDTO> dtos = entities.stream()
                .map(examinerMapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }
}

package org.example.reactive.school;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/schools")

public class SchoolController {

    private final SchoolMapper schoolMapper;
    private final SchoolService schoolService;


    public SchoolController(SchoolMapper schoolMapper, SchoolService schoolService) {
        this.schoolMapper = schoolMapper;
        this.schoolService = schoolService;
    }

    @PostMapping
    public SchoolDTO createSchool(@RequestBody SchoolDTO schoolDTO) {
        schoolService.save(schoolDTO);
        return schoolDTO;
    }

    @GetMapping
    public List<SchoolDTO> getAllSchools() {
        return schoolService.findAll();

    }
    
}

package org.example.reactive.school;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolService {
    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;


    public SchoolService(SchoolRepository schoolRepository, SchoolMapper schoolMapper) {
        this.schoolRepository = schoolRepository;
        this.schoolMapper = schoolMapper;
    }

    public void save(SchoolDTO schoolDTO) {
        var school = schoolMapper.toEntity(schoolDTO);
        var savedSchool =  schoolRepository.save(school);
        schoolMapper.toDTO(savedSchool);
    }

    public List<SchoolDTO> findAll() {
        return schoolRepository.findAll()
                .stream()
                .map(schoolMapper::toDTO)
                .collect(Collectors.toList());
    }
}

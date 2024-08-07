package org.example.reactive.student;

import org.springframework.stereotype.Service;

@Service
public class SchoolMapper {
    public School toEntity(SchoolDTO schoolDTO) {
        var school = new School();
        school.setName(schoolDTO.name());
        return school;
    }
    public SchoolDTO toDTO(School school) {
        return new SchoolDTO(school.getName());
    }
}

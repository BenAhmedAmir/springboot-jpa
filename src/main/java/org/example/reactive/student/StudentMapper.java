package org.example.reactive.student;

import org.example.reactive.school.School;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {
    public StudentResponseDTO toStudentResponseDTO(Student student) {
        return new StudentResponseDTO(student.getFirstname(), student.getLastname(), student.getEmail());
    }
    public Student toEntity(StudentDTO studentDTO) {
        if (studentDTO == null) {
            throw new NullPointerException("studentDTO is null");
        }
        var student = new Student();
        student.setFirstname(studentDTO.firstname());
        student.setLastname(studentDTO.lastname());
        student.setEmail(studentDTO.email());
        var school = new School();
        school.setId(studentDTO.schoolId());
        student.setSchool(school);
        return student;
    }
}

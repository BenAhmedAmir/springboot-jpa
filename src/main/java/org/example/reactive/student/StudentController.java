package org.example.reactive.student;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")

public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostMapping
    public StudentResponseDTO createStudent(@RequestBody StudentDTO studentDTO) {
        var student = toEntity(studentDTO);
        var savedStudent = studentRepository.save(student);
        return toStudentResponseDTO(savedStudent);
    }
    private Student toEntity(StudentDTO studentDTO) {
        var student = new Student();
        student.setFirstname(studentDTO.firstname());
        student.setLastname(studentDTO.lastname());
        student.setEmail(studentDTO.email());
        var school = new School();
        school.setId(studentDTO.schoolId());
        student.setSchool(school);
        return student;
    }
//    @GetMapping
//    public List<StudentResponseDTO> getAllStudents() {
//
//        var students = studentRepository.findAll();
//        return students.forEach(student -> fromEntity(student));
//    }

    private StudentResponseDTO toStudentResponseDTO(Student student) {
        return new StudentResponseDTO(student.getFirstname(), student.getLastname(), student.getEmail());
    }
}

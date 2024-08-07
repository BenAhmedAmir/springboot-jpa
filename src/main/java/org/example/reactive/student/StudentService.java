package org.example.reactive.student;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }
    public StudentResponseDTO createStudent(StudentDTO studentDTO) {
        var student = studentMapper.toEntity(studentDTO);
        var savedStudent = studentRepository.save(student);
        return studentMapper.toStudentResponseDTO(savedStudent);
    }
    public StudentResponseDTO findById(Integer id){
        Optional <Student> student = studentRepository.findById(id);
        return studentMapper.toStudentResponseDTO(student.orElse(new Student()));
    }

    public List<StudentResponseDTO> findAll() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toStudentResponseDTO)
                .collect(Collectors.toList());
    }

    public void deleteById(Integer studentId) {
        studentRepository.deleteById(studentId);
    }

    public List<StudentResponseDTO> findAllByFirstName(String name) {
        return studentRepository.findAllByFirstnameContaining(name)
                .stream()
                .map(studentMapper::toStudentResponseDTO)
                .collect(Collectors.toList());
    }
}

package org.example.reactive.student;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public StudentResponseDTO createStudent(@RequestBody StudentDTO studentDTO) {
        return this.studentService.createStudent(studentDTO);
    }

    @GetMapping
    public List<StudentResponseDTO> getAllStudents() {
        return this.studentService.findAll();
    }

    @GetMapping("/{studentId}")
    public StudentResponseDTO getStudentById(@PathVariable Integer studentId) throws Exception {
        return this.studentService.findById(studentId);
    }

    @DeleteMapping("/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudentById(@PathVariable("studentId") Integer id) throws Exception {
        this.studentService.deleteById(id);
    }

    @GetMapping("/search/{studentName}")
    public List<StudentResponseDTO> searchStudentByName(@PathVariable("studentName") String name) {
        return this.studentService.findAllByFirstName(name);
    }

}

package org.example.reactive.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class StudentServiceTest {
    // which service I want to test
    @InjectMocks
    private StudentService studentService;

    // declare the dependencies
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private StudentMapper studentMapper;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveStudent() {
        //Given
        StudentDTO studentDTO = new StudentDTO("Amir", "Ben Ahmed","ben@amir.ben",1);

        Student student = new Student("Amir","Ben Ahmed",12,"ben@amir.ben");
        Student savedStudent = new Student("Amir","Ben Ahmed",12,"ben@amir.ben");
        savedStudent.setId(1);

        when(studentMapper.toStudentResponseDTO(savedStudent))
                .thenReturn(new StudentResponseDTO("Amir","Ben Ahmed","ben@amir.ben"));

        //Mock the calls
        when(studentMapper.toEntity(studentDTO)).thenReturn(student);
        when(studentRepository.save(student)).thenReturn(savedStudent);
        //When
        StudentResponseDTO studentResponseDTO = studentService.createStudent(studentDTO);


        //Then
        assertEquals(studentDTO.firstname(),studentResponseDTO.firstname());
        assertEquals(studentDTO.lastname(),studentResponseDTO.lastname());
        assertEquals(studentDTO.email(),studentResponseDTO.email());

        verify(studentMapper, times(1)).toEntity(studentDTO);
        verify(studentRepository, times(1)).save(student);
        verify(studentMapper, times(1)).toStudentResponseDTO(savedStudent);
    }

    @Test
    void shouldGetAllStudents() {
        List<StudentDTO> studentDTO = new ArrayList<>();
        List<Student> students = new ArrayList<>();

        studentDTO.add(new StudentDTO("Amir", "Ben Ahmed","ben@amir.ben",1));
        students.add(new Student("Amir","Ben Ahmed",12,"ben@amir.ben"));

        when(studentRepository.findAll()).thenReturn(students);
        when(studentMapper.toStudentResponseDTO(any(Student.class)))
                .thenReturn(new StudentResponseDTO("Amir","Ben Ahmed","ben@amir.ben"));

        List<StudentResponseDTO> dtos = studentService.findAll();
        assertEquals(studentDTO.size(),dtos.size());

        verify(studentRepository,times(1)).findAll();

    }

    @Test
    void shouldGetStudentById() {
        StudentDTO studentDTO = new StudentDTO("Amir", "Ben Ahmed","ben@amir.ben",1);

        Student student = new Student("Amir","Ben Ahmed",12,"ben@amir.ben");
        student.setId(1);

        when(studentRepository.findById(1))
                .thenReturn(Optional.of(student));
        when(studentMapper.toStudentResponseDTO(any(Student.class)))
                .thenReturn(new StudentResponseDTO("Amir", "Ben Ahmed","ben@amir.ben"));
        StudentResponseDTO dto = studentService.findById(1);

        assertEquals(student.getFirstname(),dto.firstname());
        assertEquals(student.getLastname(),dto.lastname());
        assertEquals(student.getEmail(),dto.email());
    }
    @Test
    void shouldGetStudentByName() {
        List<StudentDTO> studentDTO = new ArrayList<>();
        List<Student> students = new ArrayList<>();
        studentDTO.add(new StudentDTO("ora", "Ben Ahmed","ben@amir.ben",1));
        students.add(new Student("ora","Ben Ahmed",12,"ben@amir.ben"));

        when(studentRepository.findAllByFirstnameContaining("Am")).thenReturn(students);
        when(studentMapper.toStudentResponseDTO(any(Student.class)))
                .thenReturn(new StudentResponseDTO("Amir","Ben Ahmed","ben@amir.ben"));

        List<StudentResponseDTO> dtos = studentService.findAllByFirstName("Am");
        assertEquals(studentDTO.size(),dtos.size());

        verify(studentRepository,times(1)).findAllByFirstnameContaining("Am");
    }
}
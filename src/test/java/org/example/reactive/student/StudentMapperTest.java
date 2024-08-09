package org.example.reactive.student;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {

    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        studentMapper = new StudentMapper();
    }

    @Test
    void shouldMapStudentDtoToStudent() {
        // Given
        StudentDTO studentDTO = new StudentDTO("AMIR","BEN AHMED","ben@amir.fr",1);

        // When
        Student student = studentMapper.toEntity(studentDTO);

        // Then
        assertEquals(studentDTO.firstname(),student.getFirstname());
        assertEquals(studentDTO.lastname(),student.getLastname());
        assertEquals(studentDTO.email(),student.getEmail());
        assertNotNull(student.getSchool());
        assertEquals(studentDTO.schoolId(),student.getSchool().getId());
    }

    @Test
    void shouldMapStudentToStudentDto() {
        // Given
        Student student = new Student("Amir","Ben AhMED", 10 ,"benahmed@amir.tn");

        // When
        StudentResponseDTO studentDTO = studentMapper.toStudentResponseDTO(student);
         // Then
        assertEquals(studentDTO.firstname(),student.getFirstname());
        assertEquals(studentDTO.lastname(),student.getLastname());
        assertEquals(studentDTO.email(),student.getEmail());
    }
    @Test
    void should_throw_null_pointer_exception_studentDto_to_student_when_studentDto_is_null() {
        var msg = assertThrows(NullPointerException.class, () -> studentMapper.toEntity(null));
       assertEquals(msg.getMessage(),"studentDTO is null");
    }
}
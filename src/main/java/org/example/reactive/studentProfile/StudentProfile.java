package org.example.reactive.studentProfile;

import jakarta.persistence.*;
import org.example.reactive.student.Student;

@Entity
@Table
public class StudentProfile {
    @Id
    @GeneratedValue
    private Integer Id;
    private String bio;
    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public StudentProfile() {
    }

    public StudentProfile(String bio, Student student) {
        this.bio = bio;
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}

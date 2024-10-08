package org.example.reactive.school;

import jakarta.persistence.*;
import org.example.reactive.student.Student;

import java.util.List;

@Entity
@Table
public class School {
    @Id
    @GeneratedValue
    Integer Id;
    String name;
    @OneToMany(
            mappedBy = "school"
    )
    List<Student> students;

    public School() {
    }

    public School(String name, List<Student> students) {
        this.name = name;
        this.students = students;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}

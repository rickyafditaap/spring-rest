package com.sister.siasat.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "departments")
public class Department {

    @Id
    @JsonProperty("id")
    @Column(name = "id")
    private Integer id;

    @JsonProperty("name")
    @Column(name = "name", nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(
            targetEntity = Student.class,
            mappedBy = "department",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH},
            orphanRemoval = true
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Student> students = new ArrayList<>();

    private Department() {
    }

    public Department(Integer id, String name, List<Student> students) {
        this.id = id;
        this.name = name;
        this.students.addAll(students);
        this.students.forEach(student -> student.setDepartment(this));
    }

    @JsonProperty("totalStudents")
    public Integer getTotalStudents() {
        return this.students.size();
    }

    public void addStudent(Student student) {
        student.setDepartment(this);
        this.students.add(student);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Department)) return false;
        return (this.id != null) && (this.id.equals(((Department) object).getId()));
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}

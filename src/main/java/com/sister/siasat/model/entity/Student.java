package com.sister.siasat.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sister.siasat.model.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "students")
public class Student {

    @Id
    @JsonProperty("id")
    @Column(name = "id")
    private Integer id;

    @JsonProperty("name")
    @Column(name = "name", nullable = false)
    private String name;

    @JsonProperty("gender")
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @JsonProperty("birthDate")
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @JsonIgnore
    @ManyToOne(
            targetEntity = Department.class,
            fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}
    )
    @JoinColumn(name = "department_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Department department;

    private Student() {
    }

    public Student(Integer id, String name, Gender gender, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Student)) return false;
        return (this.id != null) && (this.id.equals(((Student) object).getId()));
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}

package com.sister.siasat.controller;

import com.sister.siasat.component.StudentResourceAssembler;
import com.sister.siasat.database.repository.StudentRepository;
import com.sister.siasat.exception.StudentNotFoundException;
import com.sister.siasat.model.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(produces = "application/json")
@CrossOrigin(origins = "http://localhost:81")
public class StudentController {

    private final StudentRepository SR;
    private final StudentResourceAssembler SRA;

    @Autowired
    public StudentController(
            StudentRepository SR,
            StudentResourceAssembler SRA
    ) {
        this.SR = SR;
        this.SRA = SRA;
    }

    @GetMapping("/student/{id}")
    public Resource<Student> readOne(
            @PathVariable Integer id
    ) {
        Student student = SR.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        return SRA.toResource(student);
    }

    @PatchMapping("/student/{id}")
    public ResponseEntity<?> update(
            @PathVariable Integer id,
            @RequestBody Student newStudent
    ) throws URISyntaxException {
        Resource<Student> studentResource = SR.findById(id).map(student -> {
            student.setId(newStudent.getId());
            student.setName(newStudent.getName());
            student.setGender(newStudent.getGender());
            student.setBirthDate(newStudent.getBirthDate());
            SR.save(student);
            return SRA.toResource(student);
        }).orElseThrow(() -> new StudentNotFoundException(id));
        return ResponseEntity
                .created(new URI(studentResource.getId().expand().getHref()))
                .body(studentResource);
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<?> delete(
            @PathVariable Integer id
    ) {
        Student student = SR.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        SR.delete(student);
        return ResponseEntity
                .noContent()
                .build();
    }

}

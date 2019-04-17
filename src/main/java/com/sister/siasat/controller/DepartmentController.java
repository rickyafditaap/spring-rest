package com.sister.siasat.controller;

import com.google.common.collect.Lists;
import com.sister.siasat.component.DepartmentResourceAssembler;
import com.sister.siasat.component.DepartmentResourcesAssembler;
import com.sister.siasat.component.StudentResourceAssembler;
import com.sister.siasat.database.repository.DepartmentRepository;
import com.sister.siasat.database.repository.StudentRepository;
import com.sister.siasat.exception.DepartmentNotFoundException;
import com.sister.siasat.model.entity.Department;
import com.sister.siasat.model.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(produces = "application/json")
@CrossOrigin(origins = "http://localhost:81")
public class DepartmentController {

    private final DepartmentRepository DR;
    private final DepartmentResourceAssembler DRA;
    private final DepartmentResourcesAssembler DRAs;
    private final StudentRepository SR;
    private final StudentResourceAssembler SRA;
    private final PagedResourcesAssembler<Student> PSRA;

    @Autowired
    public DepartmentController(
            DepartmentRepository DR,
            DepartmentResourceAssembler DRA,
            DepartmentResourcesAssembler DRAs,
            StudentRepository SR,
            StudentResourceAssembler SRA,
            PagedResourcesAssembler<Student> PSRA
    ) {
        this.DR = DR;
        this.DRA = DRA;
        this.DRAs = DRAs;
        this.SR = SR;
        this.SRA = SRA;
        this.PSRA = PSRA;
    }

    @GetMapping("/departments")
    public Resources<Resource<Department>> readAll() {
        List<Department> departmentList = Lists.newArrayList(DR.findAll());
        Stream<Resource<Department>> resourceStream = departmentList.stream().map(DRA::toResource);
        List<Resource<Department>> resourceList = resourceStream.collect(Collectors.toList());
        return DRAs.toResource(resourceList);
    }

    @GetMapping("/department/{id}")
    public Resource<Department> readOne(
            @PathVariable Integer id
    ) {
        Department department = DR.findById(id).orElseThrow(() -> new DepartmentNotFoundException(id));
        return DRA.toResource(department);
    }

    @GetMapping("/department/{id}/students")
    public PagedResources readAllStudents(
            @PathVariable Integer id,
            Pageable pageable
    ) {
        Department department = DR.findById(id).orElseThrow(() -> new DepartmentNotFoundException(id));
        return PSRA.toResource(SR.findByDepartment(department, pageable), SRA);
    }

    @GetMapping("/department/{id}/students/searchByName")
    public PagedResources readAllStudents(
            @PathVariable Integer id,
            @RequestParam(value = "keyword") String name,
            Pageable pageable
    ) {
        Department department = DR.findById(id).orElseThrow(() -> new DepartmentNotFoundException(id));
        return PSRA.toResource(SR.findByDepartmentAndNameContains(department, name, pageable), SRA);
    }

    @PostMapping("/department/{id}/students")
    public ResponseEntity<?> createStudent(
            @PathVariable Integer id,
            @RequestBody Student newStudent
    ) throws URISyntaxException {
        Department department = DR.findById(id).orElseThrow(() -> new DepartmentNotFoundException(id));
        department.addStudent(newStudent);
        DR.save(department);
        Resource<Student> studentResource = SRA.toResource(newStudent);
        return ResponseEntity
                .created(new URI(studentResource.getId().expand().getHref()))
                .body(studentResource);
    }

}

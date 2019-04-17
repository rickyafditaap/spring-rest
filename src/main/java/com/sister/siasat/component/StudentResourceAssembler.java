package com.sister.siasat.component;

import com.sister.siasat.controller.DepartmentController;
import com.sister.siasat.controller.StudentController;
import com.sister.siasat.model.entity.Student;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class StudentResourceAssembler implements ResourceAssembler<Student, Resource<Student>> {

    @Override
    public Resource<Student> toResource(Student student) {
        return new Resource<>(
                student,
                linkTo(methodOn(StudentController.class).readOne(student.getId())).withSelfRel(),
                linkTo(methodOn(DepartmentController.class).readOne(
                        student.getDepartment().getId()
                )).withRel("department")
        );
    }

}

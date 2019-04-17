package com.sister.siasat.component;

import com.sister.siasat.controller.DepartmentController;
import com.sister.siasat.model.entity.Department;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class DepartmentResourceAssembler implements ResourceAssembler<Department, Resource<Department>> {

    @Override
    public Resource<Department> toResource(Department department) {
        return new Resource<>(
                department,
                linkTo(methodOn(DepartmentController.class).readOne(department.getId())).withSelfRel(),
                linkTo(methodOn(DepartmentController.class).readAllStudents(
                        department.getId(),
                        Pageable.unpaged()
                )).withRel("students")
        );
    }

}

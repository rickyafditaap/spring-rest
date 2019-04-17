package com.sister.siasat.component;

import com.sister.siasat.controller.DepartmentController;
import com.sister.siasat.model.entity.Department;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class DepartmentResourcesAssembler implements ResourceAssembler<List<Resource<Department>>, Resources<Resource<Department>>> {

    @Override
    public Resources<Resource<Department>> toResource(List<Resource<Department>> list) {
        return new Resources<>(
                list,
                linkTo(methodOn(DepartmentController.class).readAll()).withSelfRel()
        );
    }

}

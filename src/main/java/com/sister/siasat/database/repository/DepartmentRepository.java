package com.sister.siasat.database.repository;

import com.sister.siasat.model.entity.Department;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends PagingAndSortingRepository<Department, Integer> {
}

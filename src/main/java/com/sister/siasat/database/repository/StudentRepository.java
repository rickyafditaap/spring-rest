package com.sister.siasat.database.repository;

import com.sister.siasat.model.entity.Department;
import com.sister.siasat.model.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StudentRepository extends PagingAndSortingRepository<Student, Integer> {

    @Transactional(readOnly = true)
    Page<Student> findByDepartment(Department department, Pageable pageable);

    @Transactional(readOnly = true)
    Page<Student> findByDepartmentAndNameContains(Department department, @Param("name") String name, Pageable pageable);

}

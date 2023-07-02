package com.ulascan.jpahibernate.relations.OneToManyBidirectional.UniversityDepartment.repository;

import com.ulascan.jpahibernate.relations.OneToManyBidirectional.UniversityDepartment.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface IDepartmentRepository extends JpaRepository<Department, Long> {
    @Query("SELECT d FROM Department d JOIN FETCH d.courses WHERE d.id = ?1")
    Department fetchDepartmentById(Long id);
}

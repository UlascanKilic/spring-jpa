package com.ulascan.jpahibernate.relations.OneToManyBidirectional.UniversityDepartment.repository;

import com.ulascan.jpahibernate.relations.OneToManyBidirectional.UniversityDepartment.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IInstructorRepository extends JpaRepository<Instructor, Long> {
    @Query("SELECT i from Instructor i JOIN FETCH i.courses WHERE i.id = ?1")
    Instructor fetchInstructorById(Long id);
}

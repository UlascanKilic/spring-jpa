package com.ulascan.jpahibernate.relations.OneToManyBidirectional.UniversityDepartment.repository;

import com.ulascan.jpahibernate.relations.OneToManyBidirectional.UniversityDepartment.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICourseRepository extends JpaRepository<Course, Long> {
    @Query("SELECT i from Course i JOIN FETCH i.department JOIN FETCH i.instructor WHERE i.id = ?1")
    Course fetchCourseById(Long id);
}

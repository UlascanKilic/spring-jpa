package com.ulascan.jpahibernate.repository;

import com.ulascan.jpahibernate.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}

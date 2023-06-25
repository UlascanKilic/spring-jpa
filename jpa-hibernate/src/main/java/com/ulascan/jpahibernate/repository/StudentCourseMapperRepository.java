package com.ulascan.jpahibernate.repository;

import com.ulascan.jpahibernate.model.StudentCourseId;
import com.ulascan.jpahibernate.model.StudentCourseMapper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCourseMapperRepository extends JpaRepository<StudentCourseMapper, StudentCourseId> {
}

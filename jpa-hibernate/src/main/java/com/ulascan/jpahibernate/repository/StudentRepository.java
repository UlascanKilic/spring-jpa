package com.ulascan.jpahibernate.repository;

import com.ulascan.jpahibernate.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}

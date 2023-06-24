package com.ulascan.jpahibernate.repository;

import com.ulascan.jpahibernate.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
}

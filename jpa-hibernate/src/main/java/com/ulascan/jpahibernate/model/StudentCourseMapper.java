package com.ulascan.jpahibernate.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "StudentCourseMapper")
@Table(name = "studentCourseMapper")

public class StudentCourseMapper {

    @EmbeddedId
    private StudentCourseId id;

    @ManyToOne
    @MapsId("studentId")
    private Student student;

    @ManyToOne
    @MapsId("courseId")
    private Course course;

    @Column(
            name = "midterm",
            nullable = true
    )
    private int midterm;

    @Column(
            name = "finalgrade",
            nullable = true
    )
    private int finalGrade;
}

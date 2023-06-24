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
    @JoinColumn(
            name = "student_id",
            foreignKey = @ForeignKey(
                    name = "mapper_student_id_fk"
            )
    )
    private Student student;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(
            name = "course_id",
            foreignKey = @ForeignKey(
                    name = "mapper_course_id_fk"
            )
    )
    private Course course;

    @Column(
            name = "grade",
            nullable = false
    )
    private int grade;
}

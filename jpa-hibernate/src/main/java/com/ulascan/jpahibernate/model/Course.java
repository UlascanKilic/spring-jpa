package com.ulascan.jpahibernate.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Course")
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Integer id;

    private String name;

    @Column(nullable = false)
    private int hours;

    @Column(nullable = false)
    private int capacity;



    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(
            name = "material_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "material_id_fk"
            )
    )
    private Material material;

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "course"
    )
    private List<StudentCourseMapper> students = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

}

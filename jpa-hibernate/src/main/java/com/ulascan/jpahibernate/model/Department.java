package com.ulascan.jpahibernate.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "Department")
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue
    @Column(
            name = "id",
            updatable = false
    )
    private Integer id;

    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "faculty_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "faculty_id_fk"
            )
    )
    private Faculty faculty;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "department",
            orphanRemoval = true)
    private Set<Course> courses = new LinkedHashSet<>();

}

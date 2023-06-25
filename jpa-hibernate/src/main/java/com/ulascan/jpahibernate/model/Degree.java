package com.ulascan.jpahibernate.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "Degree")
@Table(name = "degree")
public class Degree {

    @Id
    @GeneratedValue
    @Column(
            name = "id",
            updatable = false
    )
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "degreetype",
            nullable = false
    )
    private DegreeType degreeType;
}

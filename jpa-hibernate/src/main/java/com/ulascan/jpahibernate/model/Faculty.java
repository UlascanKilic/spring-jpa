package com.ulascan.jpahibernate.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "Faculty")
@Table(name = "faculty")
public class Faculty {

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
}

package com.ulascan.jpahibernate.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Material {

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

    @Column(
            name = "url",
            nullable = false
    )
    private String url;
}

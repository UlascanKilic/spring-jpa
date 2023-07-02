package com.ulascan.jpahibernate.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue
    @Column(
            name = "id",
            updatable = false
    )
    private Integer id;

    @Column(
            name = "firstname",
            nullable = false
    )
    private String firstname;

    @Column(
            name = "lastname",
            nullable = false
    )
    private String lastname;

    @Column(
            name = "email",
            nullable = false,
            unique = true
    )
    private String email;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "degree_id"
    )
    private Degree degree;

    @OneToOne()
    @JoinColumn(
            name = "department_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "department_id_fk"
            )
    )
    private Department department;
}

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
    private Integer id;

    private String firstname;

    private String lastname;

    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "degree_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "degree_id_fk"
            )
    )
    private Degree degree;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "department_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "department_id_fk"
            )
    )
    private Department department;
}

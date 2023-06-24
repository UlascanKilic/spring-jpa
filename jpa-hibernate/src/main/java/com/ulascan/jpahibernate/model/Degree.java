package com.ulascan.jpahibernate.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private Integer id;

    private DegreeType degreeType;
}

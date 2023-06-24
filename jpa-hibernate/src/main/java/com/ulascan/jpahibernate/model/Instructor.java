package com.ulascan.jpahibernate.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.LinkedHashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity(name = "Instructor")
@Table(name = "instructor")
public class Instructor extends BaseEntity{

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "instructor",
            orphanRemoval = true)
    private Set<Course> courses = new LinkedHashSet<>();

}

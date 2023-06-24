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
@Entity(name = "Faculty")
@Table(name = "faculty")
public class Faculty {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;
}

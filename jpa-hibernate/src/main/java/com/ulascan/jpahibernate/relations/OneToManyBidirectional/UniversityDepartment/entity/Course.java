package com.ulascan.jpahibernate.relations.OneToManyBidirectional.UniversityDepartment.entity;

import com.ulascan.jpahibernate.relations.OneToManyBidirectional.BookStore.entity.Book;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Course {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;

    private int capacity;
    private int credit;


    // parent department
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        if(this == obj){
            return true;
        }
        if(getClass() != obj.getClass()){
            return false;
        }

        return id != null && id.equals(((Course) obj).id);
    }

    @Override
    public int hashCode(){
        return 2023;
    }
}

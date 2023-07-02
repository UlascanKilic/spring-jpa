package com.ulascan.jpahibernate.relations.OneToManyBidirectional.UniversityDepartment.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Instructor {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String degree;
    private String gradSchool;
    private int age;

    /* ? @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;*/

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false, // ! orphanRemoval = false 'cause we don't want to remove course from db
    mappedBy = "instructor")
    private List<Course> courses = new ArrayList<>();

    //helper methods

    public void addCourse(Course course){
        this.courses.add(course);
        course.setInstructor(this);
    }

    public void removeCourse(Course course){
        course.setInstructor(null);
        this.courses.remove(course);
    }

    public void removeAllCourses(){
        Iterator<Course> iterator = this.courses.iterator();
        while(iterator.hasNext()){
            Course book = iterator.next();
            book.setInstructor(null);
            iterator.remove();
        }
    }

}

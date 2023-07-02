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
public class Department {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String facultyName;
    private int yearOfFoundation;

    // ? one to many Instructor
    /*@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,
            mappedBy = "department")
    private List<Instructor> instructors = new ArrayList<>();*/

    //one to many Course
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,
            mappedBy = "department")
    private List<Course> courses = new ArrayList<>();

    //helper methods

    public void addCourse(Course course){
        this.courses.add(course);
        course.setDepartment(this);
    }

    public void removeCourse(Course course){
        course.setDepartment(null);
        course.setInstructor(null);
        this.courses.remove(course);
    }

    public void removeAllCourses(){
        Iterator<Course> iterator = this.courses.iterator();
        while(iterator.hasNext()){
            Course course = iterator.next();
            course.setDepartment(null);
            course.setInstructor(null);
            iterator.remove();
        }
    }

}

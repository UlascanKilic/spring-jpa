package com.ulascan.jpahibernate.relations.OneToManyBidirectional.UniversityDepartment.service;

import com.github.javafaker.Faker;
import com.ulascan.jpahibernate.relations.OneToManyBidirectional.UniversityDepartment.entity.Course;
import com.ulascan.jpahibernate.relations.OneToManyBidirectional.UniversityDepartment.entity.Department;
import com.ulascan.jpahibernate.relations.OneToManyBidirectional.UniversityDepartment.entity.Instructor;
import com.ulascan.jpahibernate.relations.OneToManyBidirectional.UniversityDepartment.repository.ICourseRepository;
import com.ulascan.jpahibernate.relations.OneToManyBidirectional.UniversityDepartment.repository.IDepartmentRepository;
import com.ulascan.jpahibernate.relations.OneToManyBidirectional.UniversityDepartment.repository.IInstructorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UniversityService {
    private final IDepartmentRepository departmentRepository;
    private final IInstructorRepository instructorRepository;
    private final ICourseRepository courseRepository;

    private final Faker faker;

    public UniversityService(IDepartmentRepository departmentRepository,
                             IInstructorRepository instructorRepository,
                             ICourseRepository courseRepository) {
        this.departmentRepository = departmentRepository;
        this.instructorRepository = instructorRepository;
        this.courseRepository = courseRepository;

        faker = new Faker();
    }

    public void addDepartment(){
        Department department = Department.builder()
                .name("Computer Engineering")
                .facultyName(faker.university().name())
                .yearOfFoundation(faker.random().nextInt(1900,2000))
                .courses(new ArrayList<>())
                .build();

        Instructor instructor = Instructor.builder()
                .name(faker.name().fullName())
                .degree("Msc")
                .gradSchool(faker.educator().university())
                .age(faker.random().nextInt(40,60))
                .courses(new ArrayList<>())
                .build();

        instructorRepository.save(instructor);

        for(int i = 1; i < 5; i++){
            Course course = Course.builder()
                    .name(faker.educator().course())
                    .description(faker.educator().course() + " Description")
                    .capacity(faker.random().nextInt(20,100))
                    .credit(faker.random().nextInt(1,4))
                    .build();

            department.addCourse(course);
            instructor.addCourse(course);
        }

        departmentRepository.save(department);
    }

    public void getDepartmentById(Long id){
        Department department = departmentRepository.fetchDepartmentById(id);

        System.out.println(department.getName());

        /*
        ! Throw LazyInit error courses
        */
        Department department1 = departmentRepository.findAll().get(0);

        System.out.println(department1.getName());

    }

    public void getInstructorById(Long id){
        Instructor instructor = instructorRepository.fetchInstructorById(id);

        System.out.println(instructor.getName());

        /*
        ! Throw LazyInit error for courses
        */
        Instructor instructor1 = instructorRepository.findAll().get(0);

        System.out.println(instructor1.getName());
    }

    public void getCourseById(Long id){
        Course course = courseRepository.fetchCourseById(id);

        System.out.println(course.getName());

        /*
        ! Throw LazyInit error for department and instructor
        */
        Course course1 = courseRepository.findAll().get(0);

        System.out.println(course1.getName());
    }

    @Transactional
    public void deleteCourseFromDepartment(){
        Department department = departmentRepository.fetchDepartmentById(1L);
        Course course = department.getCourses().get(0);

        department.removeCourse(course);
    }

    @Transactional
    public void removeCourseFromInstructor()
    {
        Instructor instructor = instructorRepository.fetchInstructorById(1L);
        Course course = instructor.getCourses().get(0);

        instructor.removeCourse(course);

    }
}

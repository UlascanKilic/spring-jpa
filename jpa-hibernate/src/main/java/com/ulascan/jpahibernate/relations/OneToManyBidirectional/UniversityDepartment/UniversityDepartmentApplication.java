package com.ulascan.jpahibernate.relations.OneToManyBidirectional.UniversityDepartment;

import com.ulascan.jpahibernate.relations.OneToManyBidirectional.UniversityDepartment.service.UniversityService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UniversityDepartmentApplication {

    private final UniversityService universityService;

    public UniversityDepartmentApplication(UniversityService universityService) {
        this.universityService = universityService;
    }

    public static void main(String[] args) {
        SpringApplication.run(UniversityDepartmentApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {
            universityService.addDepartment();

            universityService.getDepartmentById(1L);

            universityService.getInstructorById(1L);

            universityService.getCourseById(1L);

            universityService.deleteCourseFromDepartment();

            universityService.removeCourseFromInstructor();

        };
    }
}

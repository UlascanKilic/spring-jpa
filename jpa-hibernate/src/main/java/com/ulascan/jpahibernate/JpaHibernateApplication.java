package com.ulascan.jpahibernate;

import com.github.javafaker.Faker;
import com.ulascan.jpahibernate.model.*;
import com.ulascan.jpahibernate.repository.CourseRepository;
import com.ulascan.jpahibernate.repository.InstructorRepository;
import com.ulascan.jpahibernate.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.*;

@SpringBootApplication
public class JpaHibernateApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaHibernateApplication.class, args);
	}


	@Bean
	CommandLineRunner commandLineRunner(
			StudentRepository studentRepository,
			CourseRepository courseRepository,
			InstructorRepository instructorRepository
	)
	{
		return args -> {
			Faker faker = new Faker();

			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();
			String email = String.format("%s.%s@gmail.com", firstName, lastName);

			System.out.println(email);

			Faculty faculty = Faculty.builder()
					.name("Electric Electronic Faculty")
					.build();

			Department department = Department.builder()
					.name("Computer Engineering")
					.faculty(faculty)
					.build();

			Course course = Course.builder()
					.hours(15)
					.capacity(100)
					.department(department)
					.name("Database Systems 101")
					.material(ImageMaterial.builder()
							.url("youtube.com")
							.name("Youtube Video")
							.type(ImageType.PNG)
							.build())
					.build();

			Set<Course> courseSet = new HashSet<>();
			courseSet.add(course);

			Instructor instructor = Instructor.builder()
					.firstname(faker.name().firstName())
					.lastname(faker.name().lastName())
					.department(department)
					.email("instructor@edu.com")
					.build();



			Student student = Student.builder()
					.firstname(firstName)
					.lastname(lastName)
					.email(email)
					.degree(Degree.builder()
							.degreeType(DegreeType.BACHELORS)
							.build())
					.department(department)
					.grade(4)
					.build();

			List<StudentCourseMapper> courseMapperList = new ArrayList<>();
			courseMapperList.add(StudentCourseMapper
					.builder()
					.id(new StudentCourseId(1,1))
					.grade(2)
					.course(course)
					.student(student)
					.build());

			student.setCourses(courseMapperList);
			course.setStudents(courseMapperList);

			department.setCourses(courseSet);


			studentRepository.save(student);

			Student student1 = studentRepository.findAll().get(0);

			System.out.printf(student1.getCourses().get(0).getCourse().getName());


		};
	}
}

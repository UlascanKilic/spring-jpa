package com.ulascan.jpahibernate;

import com.github.javafaker.Faker;
import com.ulascan.jpahibernate.model.*;
import com.ulascan.jpahibernate.repository.CourseRepository;
import com.ulascan.jpahibernate.repository.InstructorRepository;
import com.ulascan.jpahibernate.repository.StudentCourseMapperRepository;
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
	CommandLineRunner commandLineRunner()
	{
		return args -> {
			//Faker faker = new Faker();

		};
	}
}

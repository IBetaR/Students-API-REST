package com.ibetar.studentdb;

import com.ibetar.studentdb.persistence.entity.Address;
import com.ibetar.studentdb.persistence.entity.Gender;
import com.ibetar.studentdb.persistence.entity.Student;
import com.ibetar.studentdb.persistence.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@SpringBootApplication
public class StudentdbApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentdbApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(StudentRepository repository, MongoTemplate mongoTemplate) {
		return args -> {
			Address address = new Address(
					"Argentina",
					"Rio Negro",
					"R8521"
			);
			String email = "perrito@gmail.com";
			Student student = new Student(
					"Toro",
					"Perrito",
					email,
					Gender.MALE,
					address,
					List.of("Technical Thermodynamics", "Maths", "Java Programming"),
					BigDecimal.valueOf(1500),
					LocalDateTime.now()
			);
			//usingMongoTemplateAndQuery(repository, mongoTemplate, email, student);
			repository.findStudentByEmail(email)
					.ifPresentOrElse(s -> {
						System.out.println(s + "already exist in Database");
					}, () -> {
						System.out.println("Inserting student into Database..." + student);
						repository.insert(student);
					});
		};
	}

//		private void usingMongoTemplateAndQuery(StudentRepository repository, MongoTemplate mongoTemplate, Student student) {
//
//			Query query = new Query();
//			String email = new String();
//			query.addCriteria(Criteria.where("email").is(email));
//
//			List<Student> students = mongoTemplate.find(query, Student.class);
//
//			if (students.size() > 1) {
//				throw new IllegalStateException("found many students with email: " + email);
//			}
//			if (students.isEmpty()) {
//				System.out.println("Inserting student into Database..." + student);
//				repository.insert(student);
//			} else {
//				System.out.println(student + "already exist in Database");
//			}
//
//			};
}




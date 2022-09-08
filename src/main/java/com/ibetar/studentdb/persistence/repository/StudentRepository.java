package com.ibetar.studentdb.persistence.repository;

import com.ibetar.studentdb.persistence.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends MongoRepository <Student, String>{

    Optional<Student> findStudentByEmail(String email);
}

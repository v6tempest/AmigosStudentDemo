package com.example.demo.student;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Data Access layer
@Repository
public interface StudentRepository extends JpaRepository<Student, Long>
{
    //@Query("SELECT s FROM Student s WHERE s.email = ?1") //Identical to the find student below statement
    Optional<Student> findStudentByEmail(String email); //Optional Class encapsulates an optional value to prevent a null value exception

}

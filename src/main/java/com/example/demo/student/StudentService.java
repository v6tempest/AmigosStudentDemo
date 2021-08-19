package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

//============ Service / Business layer =========================

@Service  //Identifies this as a class that needs to be instantiated for dependency injection
          // (i.e. spring bean) more specific as a service class
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired //Enables dependency injection of the repository
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents()
    {
        return  studentRepository.findAll();
    }

    public void addNewStudent (Student student)
    {
       Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent())
        {
            throw new IllegalStateException(("email taken"));
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId)
    {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists)
        {
            throw new IllegalStateException("Student with ID "+studentId+" does not exist");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional //Entity enters of a Manages state
    public void updateStudent(Long studentId, String lastName, String email)
    {
        Student student = studentRepository.findById(studentId).orElseThrow(()
                -> new IllegalStateException("Student with ID "+studentId+" does not exist"));
        if (lastName !=null && lastName.length() >0 && !Objects.equals(student.getLastName(), lastName))
        {
            student.setLastName(lastName);
        }
        if (email !=null && email.length() >0 && !Objects.equals(student.getEmail(), email))
        {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent())
            {
                throw new IllegalStateException("Email Taken");
            }
            student.setEmail(email);
        }
    }
}

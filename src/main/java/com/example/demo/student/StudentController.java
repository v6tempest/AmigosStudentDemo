package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//API Layer - Contains all our resources
@RestController
@RequestMapping(path ="/student" )
public class StudentController {

    private final StudentService studentService;

    @Autowired //Instantiates the class above and injects it below which is dependency injection
    public StudentController(StudentService studentService)
    {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents()
    {
        return studentService.getStudents();
    }

    //Adds a new student via POST
    @PostMapping
    public void registerNewStudent(@RequestBody Student student)
    {
        studentService.addNewStudent(student);

    }
    @DeleteMapping(path ="{studentId}")
    public void deleteStudent (@PathVariable("studentId") Long studentId)
    {
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}") //Update name and email via PUT
    public void updateStudent(
                              @PathVariable("studentId") Long studentId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email)
    {
        studentService.updateStudent(studentId, name,email);
    }
}

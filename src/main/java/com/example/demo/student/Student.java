package com.example.demo.student;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

//The defined Model
@Entity(name="Student") //maps this object to the database
@Table(
        name="student",
        uniqueConstraints = {@UniqueConstraint(name="studentEmailUnique", columnNames = "email" )}
)
public class Student {
    // enables code first generate data in DB
    @Id //specifies the primary key to be used
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"

    )
    //Variables

    @Column(
            name="id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "firstName",
            nullable = false,
            columnDefinition = "TEXT"
    )

    private String firstName;

    @Column(
            name = "lastName",
            nullable = false,
            columnDefinition = "TEXT"
    )

    private String lastName;

    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "TEXT"
    )

    private String email;

    @Column(
            name = "DateOfBirth"
    )

    private LocalDate DOB;

    @Transient // keeps age field from being added to the database
    private Integer age;

    //Three Constructors
    public Student() {
    }

    /*public Student(Long id, String firstName, String lastName, String email, LocalDate DOB) {
        this.id = id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.email = email;
        this.DOB = DOB;

    }*/

    public Student(String firstName, String lastName, String email, LocalDate DOB) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.DOB = DOB;

    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDOB() {
        return DOB;
    }

    public void setDOB(LocalDate DOB) {
        this.DOB = DOB;
    }

    public Integer getAge() {
        return Period.between(DOB, LocalDate.now()).getYears();//Calculates the age
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", DOB=" + DOB +
                ", age=" + age +
                '}';
    }
}

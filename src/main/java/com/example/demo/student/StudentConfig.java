package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student marcos = new Student( "Marcos", "Serrano","marcos@gmail.com", LocalDate.of(1975, Month.MAY, 19) );

            Student kaori = new Student( "Kaori","Oshiro", "kaori@gmail.com", LocalDate.of(1974, Month.JUNE, 25) );

            repository.saveAll(List.of(marcos, kaori));
        };
    }


}

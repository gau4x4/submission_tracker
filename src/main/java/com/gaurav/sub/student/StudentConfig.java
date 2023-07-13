package com.gaurav.sub.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import static java.time.Month.APRIL;

@Configuration
public class StudentConfig {
//    @Bean
//    CommandLineRunner commandLineRunner(StudentRepository repository) {
//        return args -> {
//            Student student = new Student(
//                    "Gaurav Pathak",
//                    Period.between(LocalDate.now(), LocalDate.of(1994, APRIL, 22)).getYears(),
//                    "gauravuf1@gmail.com", LocalDate.of(1994, APRIL, 22)
//            );
//            Student student1 = new Student(
//                    "Anubhav",
//                    Period.between(LocalDate.now(), LocalDate.of(1994, APRIL, 22)).getYears(),
//                    "gauravuf@gmail.com", LocalDate.of(1994, APRIL, 22)
//            );
//            repository.saveAll(List.of(student1, student));
//        };
//    }
}

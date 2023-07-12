package com.gaurav.cis.demo.submission;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;


@Configuration
public class SubmissionConfig{
    @Bean
    CommandLineRunner commandLineRunner(SubmissionRepository repository) {
        return args -> {
            SubmissionDTO submission = new SubmissionDTO(
                    LocalDate.now(),
                    "Elcoxy",
                    "Gaurav Pathak",
                    "Jovil etc",
                    "el@em.com",
                    "3528889602",
                    "IBM Co.",
                    "Kribhco",
                    5.00,
                    true,
                    "Owner",
                    "Submitted",
                    "Spandana"
            );
            repository.saveAll(List.of(submission,submission));
        };
    }
}

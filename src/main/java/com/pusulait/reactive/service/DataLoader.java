package com.pusulait.reactive.service;

import com.github.javafaker.Faker;
import com.pusulait.reactive.model.Patient;
import com.pusulait.reactive.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DataLoader  implements ApplicationRunner {

    private final PatientRepository patientRepository;

    public void run(ApplicationArguments args) {

        Faker faker = new Faker();
        patientRepository.save(new Patient(faker.name().firstName(), faker.name().lastName()));
    }

}

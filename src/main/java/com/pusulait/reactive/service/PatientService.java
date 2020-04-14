package com.pusulait.reactive.service;

import com.pusulait.reactive.model.Patient;
import com.pusulait.reactive.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public Mono<Patient> savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Flux<Patient> findAll() {
        return patientRepository.findAll()
                .switchIfEmpty(Mono.error(new Exception("No Blog found with title Containing")));
    }

}

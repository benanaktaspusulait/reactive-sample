package com.pusulait.reactive.repository;

import com.pusulait.reactive.model.Patient;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface PatientRepository extends ReactiveCrudRepository<Patient, String> {

    Flux<Patient> findAllByForename(String forename);

    Mono<Patient> findFirstByForename(Mono<String> forename);
}
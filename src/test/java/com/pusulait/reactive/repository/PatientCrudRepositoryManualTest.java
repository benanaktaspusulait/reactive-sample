package com.pusulait.reactive.repository;

import com.pusulait.reactive.ReactiveDemoApplication;
import com.pusulait.reactive.model.IllnessType;
import com.pusulait.reactive.model.Patient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ReactiveDemoApplication.class)
public class PatientCrudRepositoryManualTest {

    @Autowired
    PatientRepository repository;

    @Test
    public void givenValue_whenFindAllByValue_thenFindAccount() {
        repository.save(new Patient( "Bill", "Gates",75, IllnessType.CORONA)).block();
        Flux<Patient> accountFlux = repository.findAllByForename("Bill");

        StepVerifier.create(accountFlux)
                .assertNext(patient -> {
                    assertEquals("Bill", patient.getForename());
                    assertEquals("Gates" , patient.getSurname());
                    assertNotNull(patient.getId());
                })
                .expectComplete()
                .verify();
    }

    @Test
    public void givenOwner_whenFindFirstByOwner_thenFindAccount() {
        repository.save(new Patient( "Bill", "Gates",75, IllnessType.CORONA)).block();
        Mono<Patient> accountMono = repository.findFirstByForename(Mono.just("Bill"));

        StepVerifier.create(accountMono)
                .assertNext(patient -> {
                    assertEquals("Bill", patient.getForename());
                    assertEquals("Gates" , patient.getSurname());
                    assertNotNull(patient.getId());
                })
                .expectComplete()
                .verify();



    }

    @Test
    public void givenAccount_whenSave_thenSaveAccount() {
        Mono<Patient> accountMono = repository.save(new Patient("Bill", "Gates",75, IllnessType.CORONA));

        StepVerifier
                .create(accountMono)
                .assertNext(patient -> assertNotNull(patient.getId()))
                .expectComplete()
                .verify();
    }


}
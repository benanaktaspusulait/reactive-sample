package com.pusulait.reactive.controller;

import com.pusulait.reactive.model.Patient;
import com.pusulait.reactive.repository.PatientRepository;
import com.pusulait.reactive.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/patients")
public class PatientController {

    private final PatientRepository patientRepository;
    private final PatientService patientService;

    @GetMapping("/{id}")
    public Mono<Patient> getPatient(@PathVariable String id) {
        return patientRepository.findById(id);
    }

    @GetMapping(path = "/live", produces = "text/event-stream")
    public Flux<Patient> getLivePatients() {
        return patientRepository.findAll();
    }

    @GetMapping
    public Flux<Patient> getPatients() {
        return patientService.findAll();
    }




}

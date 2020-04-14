package com.pusulait.reactive.service;

import com.pusulait.reactive.mapper.PatientMapper;
import com.pusulait.reactive.model.Patient;
import com.pusulait.reactive.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public Mono<Patient> save(Patient patient) {

        if (StringUtils.isNotBlank(patient.getId())) {
            return patientRepository.findByIdAndDeleteIsFalse(patient.getId())
                    .switchIfEmpty(Mono.error(new Exception("No Author found with Id: " + patient.getId())))
                    .doOnSuccess(p -> {
                        p = updatePatient(patient, p);
                        patientRepository.save(p)
                                .subscribe();
                    });
        }
        return patientRepository.save(patient);
    }

    public Flux<Patient> findAll() {
        return patientRepository.findAll()
                .switchIfEmpty(Mono.error(new Exception("No Patient found!")));
    }
    private Patient updatePatient(Patient src, Patient dest) {
        Patient patient = patientMapper.toEntity(src,dest);
        return patient;
    }


}

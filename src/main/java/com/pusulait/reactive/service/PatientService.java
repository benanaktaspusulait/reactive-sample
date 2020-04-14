package com.pusulait.reactive.service;

import com.pusulait.reactive.mapper.PatientMapper;
import com.pusulait.reactive.model.IllnessType;
import com.pusulait.reactive.model.Patient;
import com.pusulait.reactive.repository.PatientRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicInteger;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Slf4j
@Service
@AllArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final AtomicInteger counter = new AtomicInteger();

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
        Patient patient = patientMapper.toEntity(src, dest);
        return patient;
    }

    public void patientCounter(ReactiveMongoOperations template) {
        Flux<Patient> stream = patientRepository.findWithTailableCursorBy();
        //        template.tail(query(where("IllnessType").is(IllnessType.CORONA)), Patient.class);
        Disposable subscription = stream.subscribe(patient -> {
            log.warn("WARN log received: " + patient);
            counter.incrementAndGet();
        });
    }

}

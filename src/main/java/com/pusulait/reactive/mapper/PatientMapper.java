package com.pusulait.reactive.mapper;

import com.pusulait.reactive.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * mapper interface for patient
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PatientMapper extends BaseEntityMapper<Patient, Patient> {
}

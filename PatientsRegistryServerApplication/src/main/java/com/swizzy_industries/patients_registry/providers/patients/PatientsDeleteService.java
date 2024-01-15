package com.swizzy_industries.patients_registry.providers.patients;

import com.swizzy_industries.patients_registry.repositories.patients.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PatientsDeleteService {

    private PatientRepository patientRepository;

    public Mono<Void> deleteById(ObjectId id) {
        return patientRepository.deleteById(id);
    }

    public Mono<Void> deleteAll() {
        return patientRepository.deleteAll();
    }

}

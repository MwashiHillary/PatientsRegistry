package com.swizzy_industries.patients_registry.providers.patients;

import com.swizzy_industries.patients_registry.data.documents.PatientsModel;
import com.swizzy_industries.patients_registry.repositories.patients.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PatientsPostService {

    private PatientRepository patientRepository;

    public Mono<PatientsModel> savePatient(PatientsModel patient) {
        return patientRepository.save(patient);
    }
}

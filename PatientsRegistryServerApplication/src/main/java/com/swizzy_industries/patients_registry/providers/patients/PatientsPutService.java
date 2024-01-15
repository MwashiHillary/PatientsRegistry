package com.swizzy_industries.patients_registry.providers.patients;

import com.swizzy_industries.patients_registry.data.documents.PatientsModel;
import com.swizzy_industries.patients_registry.repositories.patients.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientsPutService {

    private PatientRepository patientRepository;

    public Mono<PatientsModel> updateSpecificPatient(PatientsModel patient, ObjectId id) {
        return patientRepository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty())
                .flatMap(optionalPatient -> {
                    if (optionalPatient.isPresent()) {
                        patient.set_id(id);
                        return patientRepository.save(patient);
                    }

                    return Mono.empty();
                });
    }
}

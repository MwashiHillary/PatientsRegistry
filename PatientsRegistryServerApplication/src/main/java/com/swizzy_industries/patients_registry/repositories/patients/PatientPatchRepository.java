package com.swizzy_industries.patients_registry.repositories.patients;

import com.swizzy_industries.patients_registry.data.documents.PatientsModel;
import com.swizzy_industries.patients_registry.data.documents.person.BMI;
import com.swizzy_industries.patients_registry.data.documents.person.MedicalHistory;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface PatientPatchRepository extends ReactiveMongoRepository<PatientsModel, ObjectId> {
    public Mono<PatientsModel> findPatientsModelByBmi(BMI bmi);
    public Mono<PatientsModel> findPatientsModelByHistory(MedicalHistory history);
    public Mono<PatientsModel> findPatientsModelByEmail(String email);

    public Mono<PatientsModel> findPatientsModelByPhoneNumber(Integer phoneNumber);

}

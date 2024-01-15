package com.swizzy_industries.patients_registry.repositories.patients;

import com.swizzy_industries.patients_registry.data.documents.PatientsModel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends ReactiveMongoRepository<PatientsModel, ObjectId> {
}

package com.swizzy_industries.patients_registry.repositories.patients;

import com.swizzy_industries.patients_registry.data.documents.PatientsModel;
import com.swizzy_industries.patients_registry.data.documents.person.BMI;
import com.swizzy_industries.patients_registry.data.documents.person.MedicalHistory;
import com.swizzy_industries.patients_registry.data.documents.person.Name;
import com.swizzy_industries.patients_registry.data.documents.PatientsModel;
import com.swizzy_industries.patients_registry.data.dto.person.PersonNameDTO;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public interface PatientsGetModelInterface extends ReactiveMongoRepository<PatientsModel, ObjectId> {
    public Mono<PatientsModel> findPatientsModelByName(List<Name> name);

    public Flux<PatientsModel> findPatientsModelByNameContains(List<Name> name);

    public Mono<PatientsModel> findPatientsModelByEmail(String email);

    public Flux<PatientsModel> findPatientsModelByAddress_County(String county);
    public Flux<PatientsModel> findPatientsModelByAddress_SubCounty(String subCounty);
    public Flux<PatientsModel> findPatientsModelByHistory(MedicalHistory history);

    public Flux<PatientsModel> findPatientsModelByBmi(BMI bmi);
}

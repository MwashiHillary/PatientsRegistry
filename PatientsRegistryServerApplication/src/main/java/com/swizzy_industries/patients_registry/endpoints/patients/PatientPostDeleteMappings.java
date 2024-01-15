package com.swizzy_industries.patients_registry.endpoints.patients;

import com.swizzy_industries.patients_registry.data.documents.PatientsModel;
import com.swizzy_industries.patients_registry.providers.patients.PatientsDeleteService;
import com.swizzy_industries.patients_registry.providers.patients.PatientsPostService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/patients")
public class PatientPostDeleteMappings {

    private PatientsDeleteService patientsDeleteService;
    private PatientsPostService patientsPostService;

    @PostMapping("/add_patient")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<PatientsModel> createPatient(@RequestBody PatientsModel patient) {
        return patientsPostService.savePatient(patient);
    }

    @DeleteMapping("/patient/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deletePatient(@PathVariable("id") ObjectId id) {
        return patientsDeleteService.deleteById(id);
    }

    @DeleteMapping("/patient")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteAllPatients() {
        return patientsDeleteService.deleteAll();
    }

}

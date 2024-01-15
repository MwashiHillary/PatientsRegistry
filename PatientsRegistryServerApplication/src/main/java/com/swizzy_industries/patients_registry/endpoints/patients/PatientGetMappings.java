package com.swizzy_industries.patients_registry.endpoints.patients;

import com.swizzy_industries.patients_registry.data.documents.person.BMI;
import com.swizzy_industries.patients_registry.data.documents.person.MedicalHistory;
import com.swizzy_industries.patients_registry.data.documents.person.Name;
import com.swizzy_industries.patients_registry.data.dto.PatientsDTO;
import com.swizzy_industries.patients_registry.providers.patients.PatientsGetService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/patients")
public class PatientGetMappings {
    private PatientsGetService patientsGetService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public Flux<PatientsDTO> getAllPatients(){
        return patientsGetService.findAllPatients();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<PatientsDTO> getSpecificPatient(@PathVariable ObjectId id){
        return patientsGetService.findOnePatient(id);
    }

    @GetMapping("{email}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<PatientsDTO> getPatientByEmail(@PathVariable String email) {
        return patientsGetService.getPatientByEmail(email);
    }

    @GetMapping("{patient_name}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<PatientsDTO> getSpecificPatientByName(@PathVariable List<Name> patient_name) {
        return patientsGetService.getPatientByName(patient_name);
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Flux<PatientsDTO> getPatientsWithName(@PathVariable List<Name> name) {
        return patientsGetService.getAllPatientsWithName(name);
    }

    @GetMapping("{county}")
    @ResponseStatus(HttpStatus.OK)
    public Flux<PatientsDTO> getAllPatientsFromCounty(@PathVariable String county) {
        return patientsGetService.getPatientByCounty(county);
    }

    @GetMapping("/{sub_county}")
    @ResponseStatus(HttpStatus.OK)
    public Flux<PatientsDTO> getPatientsFromSubCounty(@PathVariable String sub_county) {
        return patientsGetService.getPatientBySubCounty(sub_county);
    }

    @GetMapping("/{medical_history}")
    @ResponseStatus(HttpStatus.OK)
    public Flux<PatientsDTO> getMedicalHistoryForPatient(@PathVariable MedicalHistory medical_history) {
        return patientsGetService.getPatientByMedicalHistory(medical_history);
    }

    @GetMapping("{bmi}")
    @ResponseStatus(HttpStatus.OK)
    public Flux<PatientsDTO> getPatientsWithHighBMI(@PathVariable BMI bmi) {
        return patientsGetService.getPatientByBMI(bmi);
    }
}

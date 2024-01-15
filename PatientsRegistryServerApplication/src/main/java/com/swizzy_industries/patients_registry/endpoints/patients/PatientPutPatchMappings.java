package com.swizzy_industries.patients_registry.endpoints.patients;

import com.swizzy_industries.patients_registry.data.documents.PatientsModel;
import com.swizzy_industries.patients_registry.data.documents.person.BMI;
import com.swizzy_industries.patients_registry.data.documents.person.MedicalHistory;
import com.swizzy_industries.patients_registry.providers.patients.PatientsPatchService;
import com.swizzy_industries.patients_registry.providers.patients.PatientsPutService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/patients")
public class PatientPutPatchMappings {

    private PatientsPatchService patientsPatchService;
    private PatientsPutService patientsPutService;

    @PutMapping("/patient/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<PatientsModel> updatePatient(@PathVariable("id") ObjectId id, @RequestBody PatientsModel patient) {
        return patientsPutService.updateSpecificPatient(patient, id);
    }


    @PatchMapping("/patient/{bmi}")
    public Mono<PatientsModel> updatePatientBMI(@PathVariable BMI bmi, PatientsModel patient, ObjectId id){
        return patientsPatchService.updatePatientBMI(patient, bmi, id);
    }

    @PatchMapping("/patient/{medical_history}")
    public Mono<PatientsModel> updatePatientMedicalHistory(@PathVariable MedicalHistory medical_history, PatientsModel patient, ObjectId id){
        return patientsPatchService.updatePatientMedicalHistory(patient, medical_history, id);
    }

    @PatchMapping("/patient/{email}")
    public Mono<PatientsModel> updatePatientEmail(@PathVariable String email, PatientsModel patient, ObjectId id){
        return patientsPatchService.updatePatientEmail(patient,email,id);
    }

    @PatchMapping("/patient/{phone_number}")
    public Mono<PatientsModel> updatePatientPhoneNumber(@PathVariable Integer phone_number, PatientsModel patient, ObjectId id){
        return patientsPatchService.updateAllPatientPhone(patient,phone_number,id);
    }

}

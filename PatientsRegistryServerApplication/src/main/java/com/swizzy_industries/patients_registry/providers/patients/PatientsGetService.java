package com.swizzy_industries.patients_registry.providers.patients;

import com.swizzy_industries.patients_registry.data.documents.person.BMI;
import com.swizzy_industries.patients_registry.data.documents.person.MedicalHistory;
import com.swizzy_industries.patients_registry.data.documents.person.Name;
import com.swizzy_industries.patients_registry.data.dto.PatientsDTO;
import com.swizzy_industries.patients_registry.repositories.patients.PatientsGetModelInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientsGetService {
    private PatientsGetModelInterface patientsGetModelInterface;

    public Flux<PatientsDTO> findAllPatients() {

        Flux<PatientsDTO> patientsDTO = patientsGetModelInterface.findAll()
                .map(patient -> new PatientsDTO(
                        patient.get_id(),
                        patient.getName(),
                        patient.getAddress(),
                        patient.getEmail(),
                        patient.getCountryCode(),
                        patient.getHistory()
                ));

        log.info("The number of patients returned is {}", patientsDTO.toStream().count());

        return patientsDTO;
    }

    public Mono<PatientsDTO> findOnePatient(ObjectId id) {
        return patientsGetModelInterface.findById(id)
                .map(patient -> new PatientsDTO(
                        patient.get_id(),
                        patient.getName(),
                        patient.getAddress(),
                        patient.getEmail(),
                        patient.getCountryCode(),
                        patient.getHistory()
                ));
    }

    public Mono<PatientsDTO> getPatientByName(List<Name> name) {
        return patientsGetModelInterface.findPatientsModelByName(name)
                .map(patient -> new PatientsDTO(
                        patient.get_id(),
                        patient.getName(),
                        patient.getAddress(),
                        patient.getEmail(),
                        patient.getCountryCode(),
                        patient.getHistory()
                ));
    }

    public Flux<PatientsDTO> getAllPatientsWithName(List<Name> name) {
        return patientsGetModelInterface.findPatientsModelByNameContains(name)
                .map(patient -> new PatientsDTO(
                        patient.get_id(),
                        patient.getName(),
                        patient.getAddress(),
                        patient.getEmail(),
                        patient.getCountryCode(),
                        patient.getHistory()
                ));
    }

    public Mono<PatientsDTO> getPatientByEmail(String email) {
        return patientsGetModelInterface.findPatientsModelByEmail(email)
                .map(patient -> new PatientsDTO(
                        patient.get_id(),
                        patient.getName(),
                        patient.getAddress(),
                        patient.getEmail(),
                        patient.getCountryCode(),
                        patient.getHistory()
                ));
    }

    public Flux<PatientsDTO> getPatientByMedicalHistory(MedicalHistory history) {
        return patientsGetModelInterface.findPatientsModelByHistory(history)
                .map(patient -> new PatientsDTO(
                        patient.get_id(),
                        patient.getName(),
                        patient.getAddress(),
                        patient.getEmail(),
                        patient.getCountryCode(),
                        patient.getHistory()
                ));
    }
    public Flux<PatientsDTO> getPatientByBMI(BMI bmi) {
        return patientsGetModelInterface.findPatientsModelByBmi(bmi)
                .map(patient -> new PatientsDTO(
                        patient.get_id(),
                        patient.getName(),
                        patient.getAddress(),
                        patient.getEmail(),
                        patient.getCountryCode(),
                        patient.getHistory()
                ));
    }

    public Flux<PatientsDTO> getPatientByCounty(String county) {
        return patientsGetModelInterface.findPatientsModelByAddress_County(county)
                .map(patient -> new PatientsDTO(
                        patient.get_id(),
                        patient.getName(),
                        patient.getAddress(),
                        patient.getEmail(),
                        patient.getCountryCode(),
                        patient.getHistory()
                ));
    }

    public Flux<PatientsDTO> getPatientBySubCounty(String subCounty) {
        return patientsGetModelInterface.findPatientsModelByAddress_SubCounty(subCounty)
                .map(patient -> new PatientsDTO(
                        patient.get_id(),
                        patient.getName(),
                        patient.getAddress(),
                        patient.getEmail(),
                        patient.getCountryCode(),
                        patient.getHistory()
                ));
    }

}

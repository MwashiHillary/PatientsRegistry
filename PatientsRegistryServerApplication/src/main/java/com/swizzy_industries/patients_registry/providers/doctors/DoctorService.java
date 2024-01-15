package com.swizzy_industries.patients_registry.providers.doctors;

import com.swizzy_industries.patients_registry.data.documents.Doctor;
import com.swizzy_industries.patients_registry.data.dto.DoctorDTO;
import com.swizzy_industries.patients_registry.repositories.doctors.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private DoctorRepository doctorRepository;
    private final ReactiveMongoTemplate mongoTemplate;

    public Mono<DoctorDTO> getByDoctorsName(String username) {
        return this.mongoTemplate.findOne(query(where("username").is(username)), DoctorDTO.class);
    }

    public Flux<DoctorDTO> getAll() {
        return doctorRepository.findAll()
                .map(doctor -> new DoctorDTO(
                        doctor.getId(),
                        doctor.getUsername(),
                        doctor.getPassword(),
                        doctor.getEmail(),
                        doctor.getRoles()
                ));
    }

    public Mono<DoctorDTO> getById(ObjectId id) {
        return doctorRepository.findById(id)
                .map(doctor -> new DoctorDTO(
                        doctor.getId(),
                        doctor.getUsername(),
                        doctor.getPassword(),
                        doctor.getEmail(),
                        doctor.getRoles()
                ));
    }

    public Mono<Doctor> saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Mono<Doctor> updateDoctor(ObjectId id, Doctor doctor) {
        return doctorRepository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty())
                .flatMap(optionalDoctor -> {
                    if (optionalDoctor.isPresent()) {
                        doctor.setId(id);
                        return doctorRepository.save(doctor);
                    }

                    return Mono.empty();
                });
    }

    public Mono<Void> deleteById(ObjectId id) {
        return doctorRepository.deleteById(id);
    }

    public Mono<Void> deleteAll() {
        return doctorRepository.deleteAll();
    }
}

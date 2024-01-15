/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swizzy_industries.patients_registry.repositories.doctors;


import com.mongodb.client.result.DeleteResult;
import com.swizzy_industries.patients_registry.data.documents.Doctor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

public interface DoctorRepository extends ReactiveMongoRepository<Doctor, ObjectId> {

    public Mono<Doctor> findByUsername(String username);

}

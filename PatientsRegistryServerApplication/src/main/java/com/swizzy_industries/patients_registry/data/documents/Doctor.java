/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swizzy_industries.patients_registry.data.documents;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Collections;
import java.util.List;

@Document(collection = "doctors")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

    @MongoId
    private ObjectId id;
    private String username;

    @JsonIgnore
    private String password;

    @Email
    private String email;

    @Builder.Default()
    private boolean active = true;

    @Builder.Default()
    private List<String> roles = Collections.emptyList();

}

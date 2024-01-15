package com.swizzy_industries.patients_registry.data.documents;


import com.swizzy_industries.patients_registry.data.documents.person.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Data
@Builder
@Document(collection = "patients")
@AllArgsConstructor
@NoArgsConstructor
public class PatientsModel {
    @MongoId
    @Indexed(unique = true)
    private ObjectId _id;

    @Indexed(unique = true)
    private List<Name> name;
    private Address address;

    @Indexed(unique = true)
    private String email;
    private String countryCode;
    private Integer phoneNumber;
    private MedicalHistory history;
    private BMI bmi;

}

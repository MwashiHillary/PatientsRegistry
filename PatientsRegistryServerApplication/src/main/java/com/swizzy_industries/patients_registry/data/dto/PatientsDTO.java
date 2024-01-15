package com.swizzy_industries.patients_registry.data.dto;

import com.swizzy_industries.patients_registry.data.documents.person.Address;
import com.swizzy_industries.patients_registry.data.documents.person.MedicalHistory;
import com.swizzy_industries.patients_registry.data.documents.person.Name;
import org.bson.types.ObjectId;

import java.util.List;

public record PatientsDTO(
        ObjectId _id,
        List<Name> name,
        Address address,
        String email,
        String countryCode,
        MedicalHistory history
) {}

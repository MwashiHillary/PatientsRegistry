package com.swizzy_industries.patients_registry.data.dto;

import org.bson.types.ObjectId;

import java.util.List;

public record DoctorDTO(
        ObjectId id,
        String username,
        String password,
        String email,
        List<String> roles
) {
}

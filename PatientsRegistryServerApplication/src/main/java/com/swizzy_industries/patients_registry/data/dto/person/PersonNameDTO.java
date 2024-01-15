package com.swizzy_industries.patients_registry.data.dto.person;

public record PersonNameDTO(
        String firstName,
        String lastName,
        String otherName,
        String surName
) {
}

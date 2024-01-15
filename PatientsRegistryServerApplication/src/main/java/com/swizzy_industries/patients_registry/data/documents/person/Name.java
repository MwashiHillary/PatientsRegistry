package com.swizzy_industries.patients_registry.data.documents.person;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Name {
    private String firstName;
    private String lastName;
    private String otherName;
    private String surName;
}

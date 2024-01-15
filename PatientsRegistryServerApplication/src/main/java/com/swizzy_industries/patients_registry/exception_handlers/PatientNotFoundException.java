package com.swizzy_industries.patients_registry.exception_handlers;

public class PatientNotFoundException extends RuntimeException {
    public PatientNotFoundException(String id) {
        super("Patient with id " + id + " was not found.");
    }
}

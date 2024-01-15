package com.swizzy_industries.patients_registry.exception_handlers;

public class PatientsNotFoundException extends RuntimeException {
    public PatientsNotFoundException(String id) {
        super("Patient with id " + id + " was not found.");
    }
}

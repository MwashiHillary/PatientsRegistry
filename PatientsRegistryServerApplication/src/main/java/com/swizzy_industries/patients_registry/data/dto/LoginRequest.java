package com.swizzy_industries.patients_registry.data.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(@NotBlank String username,
                           @NotBlank String password) {
}

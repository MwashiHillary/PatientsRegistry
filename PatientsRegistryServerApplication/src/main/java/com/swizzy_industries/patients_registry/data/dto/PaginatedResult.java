package com.swizzy_industries.patients_registry.data.dto;

import java.util.List;

public record PaginatedResult<T>(List<T> data, Long count) {
}

package com.swizzy_industries.patients_registry.data.documents.person;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Address {
    private String county;
    private String subCounty;
    private Integer postalCode;
    private Integer postalAddress;
    private List<String> landmarks;
}

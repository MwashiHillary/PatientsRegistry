/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swizzy_industries.patients_registry.endpoints.doctors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/me")
public class CurrentDoctorController {

    @GetMapping("")
    public Mono<Map<String, Object>> current(@AuthenticationPrincipal Mono<Principal> principal) {
        return principal
                .map(doctor ->
                        Map.of(
                                "name", doctor.getName(),
                                "roles", AuthorityUtils.authorityListToSet(((Authentication) doctor)
                                        .getAuthorities())
                        )
                );
    }
}

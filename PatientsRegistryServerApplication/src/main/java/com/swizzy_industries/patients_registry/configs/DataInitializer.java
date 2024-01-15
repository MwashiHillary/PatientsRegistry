/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swizzy_industries.patients_registry.configs;

import com.swizzy_industries.patients_registry.data.documents.Doctor;
import com.swizzy_industries.patients_registry.repositories.doctors.DoctorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;


@Component
@Slf4j
@RequiredArgsConstructor
class DataInitializer {

    private final DoctorRepository doctorRepository;
    private final PasswordEncoder passwordEncoder;

    @EventListener(value = ApplicationReadyEvent.class)
    public void init() {
        initUsers();
    }

    private void initUsers() {
        log.info("start users initialization  ...");
        this.doctorRepository
            .deleteAll()
            .thenMany(
                Flux
                    .just("user", "admin")
                    .flatMap(
                        username -> {
                            List<String> roles = "user".equals(username)
                                ? Arrays.asList("ROLE_USER")
                                : Arrays.asList("ROLE_USER", "ROLE_ADMIN");

                            Doctor doctor = Doctor.builder()
                                .roles(roles)
                                .username(username)
                                .password(passwordEncoder.encode("password"))
                                .email(username + "@intellisoftkenya.com")
                                .build();
                            return this.doctorRepository.save(doctor);
                        }
                    )
            )
            .log()
            .subscribe(
                null,
                null,
                () -> log.info("done users initialization...")
            );
    }
}

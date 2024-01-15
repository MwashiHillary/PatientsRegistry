package com.swizzy_industries.patients_registry.endpoints.doctors;

import com.swizzy_industries.patients_registry.data.documents.Doctor;
import com.swizzy_industries.patients_registry.data.dto.DoctorDTO;
import com.swizzy_industries.patients_registry.providers.doctors.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<DoctorDTO> getByName(@PathVariable() String username) {
        return this.doctorService.getByDoctorsName(username);
    }

    @GetMapping("/all")
    @Operation(summary = "Find products", description = "Find all products in database", tags = { "product" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = DoctorDTO.class)))) })
    @ResponseStatus(HttpStatus.OK)
    public Flux<DoctorDTO> getAllDoctors() {
        return doctorService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<DoctorDTO> getSpecificDoctor(@PathVariable("id") ObjectId id) {
        return doctorService.getById(id);
    }

    @PostMapping("/doctors/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Doctor> createDoctor(@RequestBody Doctor doctor) {
        return doctorService.saveDoctor(doctor);
    }

    @PutMapping("/doctors/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Doctor> updateDoctor(@PathVariable("id") ObjectId id, @RequestBody Doctor doctor) {
        return doctorService.updateDoctor(id, doctor);
    }

    @DeleteMapping("/doctors/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteDoctor(@PathVariable("id") ObjectId id) {
        return doctorService.deleteById(id);
    }

    @DeleteMapping("/doctors")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteAllDoctors() {
        return doctorService.deleteAll();
    }

}

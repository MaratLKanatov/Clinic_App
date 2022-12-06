package kanatovm.bestclinic.api.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import kanatovm.bestclinic.model.entities.Doctor;
import kanatovm.bestclinic.model.entities.Specialization;
import kanatovm.bestclinic.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Kanatov Marat
 * @package_name kanatovm.bestclinic.api.rest
 * @date 29.11.2022
 */

@RequestMapping("/api/doctor")
@RestController
public class DoctorEndpoint {

    @Autowired
    private DoctorService doctorService;

    @Operation(summary = "Get a doctors list by specialization")
    @GetMapping("/doctors_by_specialization")
    public List<Doctor> getDoctorsBySpecialization(@Parameter(description = "id of specialization for get by") @RequestParam(name = "specialization_id") Long id) {
        Specialization specialization = new Specialization();
        specialization.setId(id);
        return doctorService.getAllDoctors().stream().filter(doctor -> doctor.getSpecialization().getId().equals(id)).collect(Collectors.toList());
    }

    @Operation(summary = "Get all doctors")
    @GetMapping("/doctors")
    public List<Doctor> findAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @Operation(summary = "Get doctor by id")
    @GetMapping("/doctors/{doctor_id}")
    public Doctor findDoctorById(@Parameter(description = "id of doctor that will be find") @PathVariable(name = "doctor_id") Long doctor_id) {
        return doctorService.getById(doctor_id);
    }

    @Operation(summary = "Create new doctor")
    @PostMapping("/doctors")
    public ResponseEntity<Doctor> createDoctor(@Parameter(description = "new doctor entity") @RequestBody Doctor doctor) {
        return ResponseEntity.ok().body(doctorService.saveDoctor(doctor));
    }

    @Operation(summary = "Create new doctor")
    @DeleteMapping("/doctors/{doctor_id}")
    public ResponseEntity<Doctor> deleteDoctor(@Parameter(description = "id of doctor that will be deleted") @PathVariable(name = "doctor_id") Long doctor_id) {
        doctorService.removeDoctor(doctor_id);
        return ResponseEntity.ok().build();
    }
}

package kanatovm.bestclinic.repository;

import kanatovm.bestclinic.model.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
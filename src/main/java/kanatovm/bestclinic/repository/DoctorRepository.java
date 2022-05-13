package kanatovm.bestclinic.repository;

import kanatovm.bestclinic.model.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
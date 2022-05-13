package kanatovm.bestclinic.repository;

import kanatovm.bestclinic.model.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
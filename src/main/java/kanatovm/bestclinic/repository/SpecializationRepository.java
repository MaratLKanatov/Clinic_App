package kanatovm.bestclinic.repository;

import kanatovm.bestclinic.model.entities.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecializationRepository extends JpaRepository<Specialization, Long> {
}
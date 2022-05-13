package kanatovm.bestclinic.repository;

import kanatovm.bestclinic.model.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
package kanatovm.bestclinic.service;

import kanatovm.bestclinic.model.entities.Appointment;
import kanatovm.bestclinic.model.entities.Specialization;
import kanatovm.bestclinic.model.entities.User;
import kanatovm.bestclinic.repository.AppointmentRepository;
import kanatovm.bestclinic.repository.SpecializationRepository;
import kanatovm.bestclinic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AppointmentRepository appointmentRepository;
    private final SpecializationRepository specializationRepository;

    @Autowired
    public UserService(UserRepository userRepository, AppointmentRepository appointmentRepository, SpecializationRepository specializationRepository) {
        this.userRepository = userRepository;
        this.appointmentRepository = appointmentRepository;
        this.specializationRepository = specializationRepository;
    }

    public User getByUsername(String username) {
        return userRepository.findByEmail(username);
    }

    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.getById(id);
    }

    public void appointmentReport(Appointment appointment) {
        appointment.setStatus("OK");
        appointmentRepository.save(appointment);
    }

    public List<Specialization> getSpecializationList() {
        return specializationRepository.findAll();
    }
}

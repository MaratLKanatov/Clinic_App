package kanatovm.bestclinic.service;

import kanatovm.bestclinic.model.dto.HourMinute;
import kanatovm.bestclinic.model.dto.PatientDTO;
import kanatovm.bestclinic.model.entities.Appointment;
import kanatovm.bestclinic.model.entities.Doctor;
import kanatovm.bestclinic.model.entities.Patient;
import kanatovm.bestclinic.model.entities.Specialization;
import kanatovm.bestclinic.repository.AppointmentRepository;
import kanatovm.bestclinic.repository.PatientRepository;
import kanatovm.bestclinic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    private final AppointmentRepository appointmentRepository;

    public PatientService(PatientRepository patientRepository, UserRepository userRepository, AppointmentRepository appointmentRepository) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public void savePatient(PatientDTO patientDTO) {
        patientRepository.save(patientDTO.getPatient());
    }

    public Patient getCurrentPatient(Authentication authentication) {
        return patientRepository.getById(userRepository.findByEmail(((UserDetails) authentication.getPrincipal()).getUsername()).getId());
    }

    public void updatePatient(Patient patient) {
        patientRepository.save(patient);
    }

    public void updatePatient(Authentication authentication, Appointment appointment) {
        Patient patient = getCurrentPatient(authentication);
        if (appointment.getPatient() == null)
            appointment.setPatient(patient);
        patient.getAppointments().add(appointment);
        appointmentRepository.save(appointment);
        patientRepository.save(patient);
    }

    public void updatePatient(Authentication authentication, Appointment appointment, String reason) {
        appointment.setReason(reason);
        updatePatient(authentication, appointment);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
}

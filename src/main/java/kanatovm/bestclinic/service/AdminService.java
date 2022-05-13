package kanatovm.bestclinic.service;

import kanatovm.bestclinic.model.entities.*;
import kanatovm.bestclinic.model.enums.Permission;
import kanatovm.bestclinic.model.enums.Role;
import kanatovm.bestclinic.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final SpecializationRepository specializationRepository;
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public AdminService(UserRepository userRepository, DoctorRepository doctorRepository, AppointmentRepository appointmentRepository, PatientRepository patientRepository, SpecializationRepository specializationRepository, ScheduleRepository scheduleRepository) {
        this.userRepository = userRepository;
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.specializationRepository = specializationRepository;
        this.scheduleRepository = scheduleRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<Specialization> getAllSpecializations() {
        return specializationRepository.findAll();
    }

    public List<Role> getAllRoles() {
        return List.of(Role.ADMIN, Role.PATIENT, Role.DOCTOR);
    }

    public List<Permission> getAllPermissions() {
        return List.of(Permission.ADMIN_INDEX, Permission.PATIENT_INDEX, Permission.DOCTOR_INDEX);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public void deleteDoctorById(Long id) {
        Doctor doctor = doctorRepository.getById(id);
        User user = doctor.getUser();
        appointmentRepository.deleteAll(doctor.getAppointments());
        doctorRepository.delete(doctor);
        userRepository.delete(user);
    }
}

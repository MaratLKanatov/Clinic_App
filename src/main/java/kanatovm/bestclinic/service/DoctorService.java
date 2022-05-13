package kanatovm.bestclinic.service;

import kanatovm.bestclinic.model.dto.DoctorStatistic;
import kanatovm.bestclinic.model.dto.HourMinute;
import kanatovm.bestclinic.model.entities.Appointment;
import kanatovm.bestclinic.model.entities.Doctor;
import kanatovm.bestclinic.model.entities.Specialization;
import kanatovm.bestclinic.repository.AppointmentRepository;
import kanatovm.bestclinic.repository.DoctorRepository;
import kanatovm.bestclinic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository, UserRepository userRepository, AppointmentRepository appointmentRepository) {
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public DoctorStatistic buildDoctorStatistic(Authentication authentication) {
        Doctor doctor = getCurrentDoctor(authentication);
        DoctorStatistic doctorStatistic = new DoctorStatistic();
        if (doctor.getAppointments() != null) {
            doctorStatistic.setPatientCount(doctor.getAppointments().size());
            doctorStatistic.setDailyPatientCount(getWaitingAppointments(doctor).stream().filter(appointment -> ((appointment.getMeetingTime().getDay()) == (new Timestamp(System.currentTimeMillis()).getDay())) ).collect(Collectors.toList()).size());
            doctorStatistic.setTodayAppointments(getWaitingAppointments(doctor).stream().filter(appointment -> ((appointment.getMeetingTime().getDay()) == (new Timestamp(System.currentTimeMillis()).getDay())) ).collect(Collectors.toList()).size());
        }
        else {
            doctorStatistic.setPatientCount(0);
            doctorStatistic.setDailyPatientCount(0);
            doctorStatistic.setTodayAppointments(0);
        }
        return doctorStatistic;
    }

    public Doctor getCurrentDoctor(Authentication authentication) {
        return doctorRepository.getById(userRepository.findByEmail(((UserDetails) authentication.getPrincipal()).getUsername()).getId());
    }

    public List<Appointment> getWaitingAppointments(Authentication authentication) {
        return getCurrentDoctor(authentication).getAppointments().stream().filter(appointment -> appointment.getStatus().equals("WAITING")).collect(Collectors.toList());
    }

    public List<Appointment> getWaitingAppointments(Doctor doctor) {
        return doctor.getAppointments().stream().filter(appointment -> appointment.getStatus().equals("WAITING")).collect(Collectors.toList());
    }

    public List<HourMinute> getHourMinutes(Long id, Date date) {
        return HourMinute.availableTimes(doctorRepository.getById(id), date);
    }

    public Doctor getById(Long id) {
        return doctorRepository.getById(id);
    }

    public List<Doctor> getDoctorsList(Specialization specialization) {
        return doctorRepository.findAll().stream().filter(doctor -> doctor.getSpecialization().getName().equals(specialization.getName())).collect(Collectors.toList());
    }

    public void saveDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    public void saveDoctorUser(Doctor doctor) {
        userRepository.save(doctor.getUser());
        doctorRepository.save(doctor);
    }
}

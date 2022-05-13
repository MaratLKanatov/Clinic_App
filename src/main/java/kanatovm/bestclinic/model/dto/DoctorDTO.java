package kanatovm.bestclinic.model.dto;

import kanatovm.bestclinic.model.entities.Doctor;
import kanatovm.bestclinic.model.entities.Specialization;
import kanatovm.bestclinic.model.entities.User;
import kanatovm.bestclinic.model.enums.Role;
import kanatovm.bestclinic.model.enums.Status;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Timestamp;
import java.util.Date;

@Setter
@Getter
public class DoctorDTO {
    private String email;
    private String password;
    private String matchingPassword;

    private String firstname;
    private String lastname;
    private String gender;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date birthdate;
    private HourMinute hourMinute;
    private String location;
    private String telnum;
    private Specialization specialization;

    public DoctorDTO() {

    }

    public Doctor getDoctor() {
        User user = new User();
        user.setEmail(email);
        user.setPassword(new BCryptPasswordEncoder(12).encode(password));
        user.setStatus(Status.ACTIVE);
        user.setRole(Role.PATIENT);

        Doctor doctor = new Doctor();

        doctor.setBirthdate(new Timestamp(birthdate.getYear(), birthdate.getMonth(), birthdate.getDate(), getHourMinute().getHour(), getHourMinute().getMinute(), 0,0));
        // Timestamp.valueOf("2018-11-12 01:02:03.123456789")

        doctor.setFirstname(firstname);
        doctor.setGender(gender);
        doctor.setLastname(lastname);
        doctor.setLocation(location);
        doctor.setTelnum(telnum);
        doctor.setUser(user);
        doctor.setSpecialization(specialization);
        return doctor;
    }
}

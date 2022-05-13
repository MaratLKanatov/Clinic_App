package kanatovm.bestclinic.model.dto;

import kanatovm.bestclinic.model.entities.Patient;
import kanatovm.bestclinic.model.entities.User;
import kanatovm.bestclinic.model.enums.Role;
import kanatovm.bestclinic.model.enums.Status;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
public class PatientDTO {

    @Email(message = "not valid email")
    private String email;

    @Size(min=7, max=16, message = "between 7 and 16 symbols")
    private String password;

    @Size(min=7, max=16, message = "between 7 and 16 symbols")
    private String matchingPassword;

    @Size(min=1, max=16, message = "between 1 and 16 symbols")
    private String firstname;

    @Size(min=1, max=16, message = "between 1 and 16 symbols")
    private String lastname;

    private String gender;

    @DateTimeFormat(pattern = "LL/dd/yyyy")
    private Date birthdate;

    private HourMinute hourMinute;

    @Size(min=1, max=255, message = "not valid address")
    private String location;

    @Size(min=11, max=12, message = "not valid telephone number")
    private String telnum;

    public PatientDTO() {
        setBirthdate(new Date());
    }

    public Patient getPatient() {
        User user = new User();
        user.setEmail(email);
        user.setPassword(new BCryptPasswordEncoder(12).encode(password));
        user.setStatus(Status.ACTIVE);
        user.setRole(Role.PATIENT);

        Patient patient = new Patient();
        patient.setBirthdate(new Timestamp(birthdate.getYear(), birthdate.getMonth(), birthdate.getDate(), 0, 0, 0,0));
        patient.setFirstname(firstname);
        patient.setGender(gender);
        patient.setLastname(lastname);
        patient.setLocation(location);
        patient.setTelnum(telnum);
        patient.setUser(user);
        return patient;
    }

    public Timestamp getTimestamp() {
        return new Timestamp(birthdate.getYear(), birthdate.getMonth(), birthdate.getDate(), hourMinute.getHour(), hourMinute.getMinute(), 0,0);
    }

    public static PatientDTO fromPatient(Patient patient) {
        PatientDTO patientDTO = new PatientDTO();
//        patientDTO.setEmail(patient.getUser().getEmail());
//        private String firstname;
//        private String lastname;
//        private String gender;
//        private Date birthdate;
//        private String location;
//        private String telnum;

        return patientDTO;
    }

    @Override
    public String toString() {
        return "PatientDTO{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", matchingPassword='" + matchingPassword + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", gender='" + gender + '\'' +
                ", birthdate=" + birthdate +
                ", location='" + location + '\'' +
                ", telnum='" + telnum + '\'' +
                '}';
    }
}

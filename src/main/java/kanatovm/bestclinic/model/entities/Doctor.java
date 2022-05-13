package kanatovm.bestclinic.model.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "doctors")
@Getter
@Setter
public class Doctor {
    @Id
    private Long id;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column
    private String gender;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthdate;

    @Column
    private String location;

    @Column
    private String telnum;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    @OneToMany(mappedBy = "doctor")
    private Set<Appointment> appointments;

    @ManyToOne
    @JoinColumn(name = "specialization_id")
    private Specialization specialization;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public Doctor(Long id, String firstname, String lastname, String gender, Date birthdate, String location, String telnum, User user, Set<Appointment> appointments, Specialization specialization) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.birthdate = birthdate;
        this.location = location;
        this.telnum = telnum;
        this.user = user;
        this.appointments = appointments;
        this.specialization = specialization;
    }

    public Doctor() {
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", gender='" + gender + '\'' +
                ", birthdate=" + birthdate +
                ", location='" + location + '\'' +
                ", telnum='" + telnum + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(id, doctor.id) && Objects.equals(firstname, doctor.firstname) && Objects.equals(lastname, doctor.lastname) && Objects.equals(gender, doctor.gender) && Objects.equals(birthdate, doctor.birthdate) && Objects.equals(location, doctor.location) && Objects.equals(telnum, doctor.telnum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, gender, birthdate, location, telnum);
    }
}
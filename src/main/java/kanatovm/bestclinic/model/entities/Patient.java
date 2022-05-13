package kanatovm.bestclinic.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "patients")
@Getter
@Setter
public class Patient {
    @Id
    @Column
    private Long id;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column
    private String gender;

    @Column
    private Timestamp birthdate;

    @Column
    private String location;

    @Column
    private String telnum;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    @OneToMany(mappedBy="patient")
    private List<Appointment> appointments;

    public Patient() {

    }

    public Patient(Long id, String firstname, String lastname, String gender, Timestamp birthdate, String location, String telnum, User user, List<Appointment> appointments) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.birthdate = birthdate;
        this.location = location;
        this.telnum = telnum;
        this.user = user;
        this.appointments = appointments;
    }

    @Override
    public String toString() {
        return "Patient{" +
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
        Patient patient = (Patient) o;
        return Objects.equals(id, patient.id) && Objects.equals(firstname, patient.firstname) && Objects.equals(lastname, patient.lastname) && Objects.equals(gender, patient.gender) && Objects.equals(birthdate, patient.birthdate) && Objects.equals(location, patient.location) && Objects.equals(telnum, patient.telnum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, gender, birthdate, location, telnum);
    }
}

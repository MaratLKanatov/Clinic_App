package kanatovm.bestclinic.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "appointments")
@Getter
@Setter
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private Timestamp meetingTime;

    @Column
    private Timestamp createdTime;

    @Column
    private String reason;

    @Column
    private String report;

    @Column
    private String status;

    @ManyToOne
    @JoinColumn(name="patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name="doctor_id")
    private Doctor doctor;

    public Appointment(Long id, Timestamp meetingTime, Timestamp createdTime, String reason, String report, String status, Patient patient, Doctor doctor) {
        this.id = id;
        this.meetingTime = meetingTime;
        this.createdTime = createdTime;
        this.reason = reason;
        this.report = report;
        this.status = status;
        this.patient = patient;
        this.doctor = doctor;
    }

    public Appointment() {
    }

    public Appointment setDateCurrent() {
        Date date = new Date();
        createdTime = new Timestamp(System.currentTimeMillis());
        meetingTime = new Timestamp(date.getYear(), date.getMonth(), date.getDate(), 0, 0, 0,0);
        return this;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", meetingTime=" + meetingTime +
                ", createdTime=" + createdTime +
                ", reason='" + reason + '\'' +
                ", report='" + report + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return Objects.equals(id, that.id) && Objects.equals(meetingTime, that.meetingTime) && Objects.equals(createdTime, that.createdTime) && Objects.equals(reason, that.reason) && Objects.equals(report, that.report) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, meetingTime, createdTime, reason, report, status);
    }

    public String getCreatedTimeString() {
        return new SimpleDateFormat("MM/dd/yyyy").format(createdTime);
    }

    public String getMeetingTimeString() {
        return new SimpleDateFormat("MM/dd/yyyy HH:mm").format(meetingTime);
    }
}

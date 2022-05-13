package kanatovm.bestclinic.model.dto;

import kanatovm.bestclinic.model.entities.Appointment;
import kanatovm.bestclinic.model.entities.Doctor;
import kanatovm.bestclinic.model.entities.Patient;
import kanatovm.bestclinic.model.entities.Specialization;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class AppointmentDTO {
    // Caclulated Value
    private Doctor doctor; // set from service
    private Patient patient; // set from service
    private Timestamp created_time; // create by String
    private Timestamp meeting_time; // create by String  + HourMinute

    private Long id;
    private String reason;
    private String report;
    private String status;
    private Long doctor_id;
    private Long patient_id;

    @DateTimeFormat(pattern = "LL/dd/yyyy")
    private Date created;
    @DateTimeFormat(pattern = "LL/dd/yyyy")
    private Date meeting;
    private String hourMinuteStr;
    private HourMinute hourMinute;
    private Specialization specialization;

    public Appointment getAppointment() {
        Appointment appointment = new Appointment();
        if (created != null) created_time = new Timestamp(created.getYear(), created.getMonth(), created.getDate(), 0, 0, 0,0);
        if (meeting != null && hourMinute != null) meeting_time = new Timestamp(meeting.getYear(), meeting.getMonth(), meeting.getDate(), hourMinute.getHour(), hourMinute.getMinute(), 0,0);

        if (id != null) appointment.setId(id);
        if (created_time != null) appointment.setCreatedTime(created_time);
        else appointment.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        if (meeting_time != null) appointment.setMeetingTime(meeting_time);
        if (reason != null) appointment.setReason(reason);
        if (report != null) appointment.setReport(report);
        if (status != null) appointment.setStatus(status);
        else appointment.setStatus("WAITING");
        if (doctor != null) appointment.setDoctor(doctor);
        if (patient != null) appointment.setPatient(patient);

        return appointment;
    }

    public Boolean getFormFillnes() {
        boolean status = doctor != null && patient != null && meeting != null && hourMinute != null;

        if (status) {
            if (getHourMinutesList().stream().filter(hourMinute1 -> hourMinute1.toString().equals(hourMinute.toString())).count() == 0)
                status = false;
        }

        return status;
    }

    public AppointmentDTO buildAppointment(Doctor doctor, Patient patient) {
        if (created != null) created_time = new Timestamp(created.getYear(), created.getMonth(), created.getDate(), 0, 0, 0,0);
        if (meeting != null && hourMinute != null) meeting_time = new Timestamp(meeting.getYear(), meeting.getMonth(), meeting.getDate(), hourMinute.getHour(), hourMinute.getMinute(), 0,0);
        if (doctor != null) this.doctor = doctor;
        if (patient != null) this.patient = patient;
        if (hourMinuteStr != null) this.hourMinute = HourMinute.fromString(hourMinuteStr);
        return this;
    }

    public List<HourMinute> getHourMinutesList() {
        if (doctor == null || this.meeting == null) {
            return new ArrayList<>();
        }
        return HourMinute.availableTimes(doctor, this.meeting);
    }

    @Override
    public String toString() {
        return "AppointmentDTO{" +
                "id=" + id +
                ", created_time=" + created_time +
                ", meeting_time=" + meeting_time +
                ", reason='" + reason + '\'' +
                ", report='" + report + '\'' +
                ", status='" + status + '\'' +
                ", doctor=" + doctor +
                ", patient=" + patient +
                ", created=" + created +
                ", meeting=" + meeting +
                ", hourMinute=" + hourMinute +
                '}';
    }
}

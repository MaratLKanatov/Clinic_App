package kanatovm.bestclinic.model.dto;

import lombok.Data;

@Data
public class DoctorStatistic {
    private int patientCount;
    private int dailyPatientCount;
    private int todayAppointments;
}

package kanatovm.bestclinic.model.dto;

import kanatovm.bestclinic.model.entities.Doctor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
public class HourMinute {
    //@Value("doctor.schedule.work-time-start")
    private static int start = 8;

    //@Value("doctor.schedule.work-time-finish")
    private static int finish = 17;

    //@Value("doctor.schedule.delay")
    private static int delay = 30;

    private int hour;
    private int minute;

    public static HourMinute fromString(String hourMinuteStr) {
        int hour = Integer.parseInt(hourMinuteStr.substring(0, hourMinuteStr.indexOf(":")));
        int minute = Integer.parseInt(hourMinuteStr.substring(hourMinuteStr.indexOf(":")+1));
        return new HourMinute(hour, minute);
    }

    @Override
    public String toString() {
        return (hour-9>0?"":"0") + hour + " : " + (minute-9>0?"":"0") + minute;
    }

    public String toString2() {
        return hour + ":"  + minute;
    }

    private HourMinute() {

    }

    private HourMinute(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public static List<HourMinute> availableTimes(Doctor doctor, Date date) {
        List<HourMinute> list = new ArrayList<>();
        int hour = start, minute = 0;

        while (hour < finish) {
            int finalHour = hour, finalMinute = minute;
            if ((doctor.getAppointments().stream()
                    .filter(appointment -> appointment.getMeetingTime().getDate() == date.getDate() && appointment.getMeetingTime().getMonth() == date.getMonth())
                    .filter(appointment -> (appointment.getMeetingTime().getHours() == finalHour && appointment.getMeetingTime().getMinutes() == finalMinute)).count() == 0))
                list.add(new HourMinute(hour, minute));
            minute += delay;
            if (minute >= 60) {
                hour++;
                minute=0;
            }
        }
        return list;
    }
}

package kanatovm.bestclinic.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "schedules")
@Getter
@Setter
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private String monday;

    @Column
    private String tuesday;

    @Column
    private String wednesday;

    @Column
    private String thursday;

    @Column
    private String friday;

    @Column
    private String saturday;

    @Column
    private String sunday;

    @OneToMany(mappedBy = "schedule")
    @JsonIgnore
    private List<Doctor> doctors;

    public Schedule(Long id, String name, String monday, String tuesday, String wednesday, String thursday, String friday, String saturday, String sunday) {
        this.id = id;
        this.name = name;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
    }

    public Schedule() {

    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", monday='" + monday + '\'' +
                ", tuesday='" + tuesday + '\'' +
                ", wednesday='" + wednesday + '\'' +
                ", thursday='" + thursday + '\'' +
                ", friday='" + friday + '\'' +
                ", saturday='" + saturday + '\'' +
                ", sunday='" + sunday + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return Objects.equals(id, schedule.id) && Objects.equals(name, schedule.name) && Objects.equals(monday, schedule.monday) && Objects.equals(tuesday, schedule.tuesday) && Objects.equals(wednesday, schedule.wednesday) && Objects.equals(thursday, schedule.thursday) && Objects.equals(friday, schedule.friday) && Objects.equals(saturday, schedule.saturday) && Objects.equals(sunday, schedule.sunday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, monday, tuesday, wednesday, thursday, friday, saturday, sunday);
    }
}

package kanatovm.bestclinic.controller;

import kanatovm.bestclinic.model.entities.Appointment;
import kanatovm.bestclinic.service.DoctorService;
import kanatovm.bestclinic.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DoctorController.class})
class DoctorControllerTest {

    @MockBean
    private DoctorService doctorService;

    @MockBean
    private UserService userService;

    @Test
    void getDashboard() {

    }

    @Test
    void getPageAppointmentHistory() {
//        when(doctorService.getCurrentDoctor(null).getAppointments()).thenReturn(new HashSet<>() {{add(new Appointment());}});
    }
}
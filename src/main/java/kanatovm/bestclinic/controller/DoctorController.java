package kanatovm.bestclinic.controller;

import kanatovm.bestclinic.model.entities.Appointment;
import kanatovm.bestclinic.service.DoctorService;
import kanatovm.bestclinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/doctor")
@PreAuthorize("hasAuthority('ALLOW_DOCTOR_INDEX')")
public class DoctorController {
    private final DoctorService doctorService;
    private final UserService userService;

    @Autowired
    public DoctorController(DoctorService doctorService, UserService userService) {
        this.doctorService = doctorService;
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public String getDashboard() {
        return "redirect:/";
    }

    @GetMapping("/appointment")
    public String getAppointmentPage(Model model, Authentication authentication) {
        model.addAttribute("appointment", doctorService.getWaitingAppointments(authentication).get(0));
        model.addAttribute("listDoctors", doctorService.getAllDoctors());
        return "appointment";
    }

    @GetMapping("/appointment/{id}")
    public String getAppointmentPageById(@ModelAttribute("id") Long id, Model model) {
        model.addAttribute("appointment", userService.getAppointmentById(id));
        model.addAttribute("listDoctors", doctorService.getAllDoctors());
        return "appointment";
    }

    @PostMapping("/appointment")
    public String postAppointmentPage(@ModelAttribute("appointment") Appointment appointment) {
        userService.appointmentReport(appointment);
        return "redirect:/doctor/dashboard";
    }

    @GetMapping("/profileEdit")
    public String getProfileEdit() {
        return "profileEdit";
    }

    @GetMapping("/history")
    public String getPageAppointmentHistory(Model model, Authentication authentication) {
        model.addAttribute("appointments", doctorService.getCurrentDoctor(authentication).getAppointments());
        return "doctorAppointmentHistory";
    }
}

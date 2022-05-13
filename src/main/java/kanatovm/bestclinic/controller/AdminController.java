package kanatovm.bestclinic.controller;

import kanatovm.bestclinic.model.entities.Doctor;
import kanatovm.bestclinic.model.entities.User;
import kanatovm.bestclinic.model.enums.Role;
import kanatovm.bestclinic.model.enums.Status;
import kanatovm.bestclinic.service.AdminService;
import kanatovm.bestclinic.service.DoctorService;
import kanatovm.bestclinic.service.PatientService;
import kanatovm.bestclinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ALLOW_ADMIN_INDEX')")
public class AdminController {
    private final UserService userService;
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final AdminService adminService;

    @Autowired
    public AdminController(UserService userService, DoctorService doctorService, PatientService patientService, AdminService adminService) {
        this.userService = userService;
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.adminService = adminService;
    }

    @GetMapping
    public String getPageAdminIndex(Model model) {
        model.addAttribute("users", adminService.getAllUsers());
        model.addAttribute("roles", adminService.getAllRoles());
        model.addAttribute("permissions", adminService.getAllPermissions());
        return "admin/dashboard/dashboard";
    }

    @GetMapping("/base")
    public String getPageBase(Model model) {
        model.addAttribute("users", adminService.getAllUsers());
        return "admin/dashboard/base";
    }

    @GetMapping("/base/new")
    public String getPageBaseNew(Model model) {
        model.addAttribute("doctor", new Doctor(){{this.setUser(new User()); this.getUser().setRole(Role.DOCTOR); this.getUser().setStatus(Status.ACTIVE);}});
        model.addAttribute("listSpecializations", adminService.getAllSpecializations());
        model.addAttribute("listSchedules", adminService.getAllSchedules());
        return "admin/form/base";
    }
    
    @GetMapping("/doctor")
    public String getPageDoctorEntity(Model model) {
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "admin/dashboard/doctor";
    }
    
    @GetMapping("/patient")
    public String getPagePatientEntity(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        return "admin/dashboard/patient";
    }
    
    @GetMapping("/specialization")
    public String getPageSpecialization(Model model) {
        model.addAttribute("specializations", adminService.getAllSpecializations());
        return "admin/dashboard/specialization";
    }
    
    @GetMapping("/appointment")
    public String getPageAppointment(Model model) {
        model.addAttribute("appointments", adminService.getAllAppointments());
        return "admin/dashboard/appointment";
    }
    
    @GetMapping("/schedule")
    public String getPageSchedule(Model model) {
        model.addAttribute("schedules", adminService.getAllSchedules());
        return "admin/dashboard/schedule";
    }

    @GetMapping("/doctor/new")
    public String getPageDoctorNew(Model model) {
        model.addAttribute("doctor", new Doctor(){{setUser(new User());}});
        model.addAttribute("listSpecializations", adminService.getAllSpecializations());
        model.addAttribute("listSchedules", adminService.getAllSchedules());
        return "admin/form/doctor";
    }

    @GetMapping("/doctor/edit/{id}")
    public String getPageDoctorEdit(@PathVariable Long id, Model model) {
        Doctor doctor = doctorService.getById(id);
        model.addAttribute("doctor", doctor);
        model.addAttribute("listSpecializations", adminService.getAllSpecializations());
        model.addAttribute("listSchedules", adminService.getAllSchedules());
        return "admin/form/doctor";
    }

    @GetMapping("/doctor/delete/{id}")
    public String getPageDoctorDelete(@PathVariable Long id) {
        adminService.deleteDoctorById(id);
        return "redirect:/admin/doctor";
    }

    @PostMapping("/doctor/new")
    public String postPageDoctorNew(@ModelAttribute("doctor") Doctor doctor) {
        doctor.getUser().setRole(Role.DOCTOR);
        if (doctor.getId() == null)
            doctor.getUser().setStatus(Status.ACTIVE);
        doctor.getUser().setPassword(new BCryptPasswordEncoder(12).encode(doctor.getUser().getPassword()));
        if (doctor.getId() != null)
            doctorService.saveDoctorUser(doctor);
        else
            doctorService.saveDoctor(doctor);
        return "redirect:/admin/doctor";
    }

    @GetMapping("/patient/new")
    public String getPagePatientNew(Model model) {
        model.addAttribute("object", new Object());
        return "admin/form/patient";
    }

    @GetMapping("/specialization/new")
    public String getPageSpecializationNew(Model model) {
        model.addAttribute("object", new Object());
        return "admin/form/specialization";
    }

    @GetMapping("/appointment/new")
    public String getPageAppointmentNew(Model model) {
        model.addAttribute("object", new Object());
        return "admin/form/appointment";
    }

    @GetMapping("/schedule/new")
    public String getPageScheduleNew(Model model) {
        model.addAttribute("object", new Object());
        return "admin/form/schedule";
    }
}

package kanatovm.bestclinic.controller;

import kanatovm.bestclinic.model.dto.AppointmentDTO;
import kanatovm.bestclinic.model.dto.PatientDTO;
import kanatovm.bestclinic.model.entities.Appointment;
import kanatovm.bestclinic.model.entities.Doctor;
import kanatovm.bestclinic.model.entities.Patient;
import kanatovm.bestclinic.service.DoctorService;
import kanatovm.bestclinic.service.PatientService;
import kanatovm.bestclinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Date;

@Controller
@PreAuthorize("hasAuthority('ALLOW_PATIENT_INDEX')")
public class PatientController {
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final UserService userService;

    @Autowired
    public PatientController(PatientService patientService, DoctorService doctorService, UserService userService) {
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.userService = userService;
    }

    @GetMapping("/patientProfile")
    public String getHomePage(Authentication authentication, Model model) {
        model.addAttribute("patient", patientService.getCurrentPatient(authentication));
        return "patientProfile";
    }

    @GetMapping("/patientProfile/edit")
    public String getPatientEdit(Model model, Authentication authentication) {
        model.addAttribute("patient", patientService.getCurrentPatient(authentication));
        return "patientProfileEdit";
    }

    @PostMapping("/patientProfile/edit")
    public String postPatientEdit(@ModelAttribute("patient") Patient patient) {
        patientService.updatePatient(patient);
        return "redirect:/patientProfile";
    }

    @GetMapping("/patient/callMedicalHelp")
    public String getNewMedicalHelpAppointment(Model model, Authentication authentication) {
        model.addAttribute("dto", new AppointmentDTO().buildAppointment(null, null));
        model.addAttribute("isNewAppointment", false);
        model.addAttribute("listSpecializations", userService.getSpecializationList());
        model.addAttribute("listDoctors", new ArrayList<Doctor>());
        return "patientNewAppointment";
    }

    @PostMapping("/patient/callMedicalHelp")
    public String postNewMedicalHelpAppointment(@ModelAttribute("dto") AppointmentDTO dto, Model model, Authentication authentication) {
        dto.buildAppointment(dto.getDoctor_id() != null ? doctorService.getById(dto.getDoctor_id()) : null, patientService.getCurrentPatient(authentication));
        dto.setReason("MEDICAL_HELP");
        model.addAttribute("isNewAppointment", false);
        model.addAttribute("listSpecializations", userService.getSpecializationList());
        model.addAttribute("listDoctors", dto.getSpecialization()!=null?doctorService.getDoctorsList(dto.getSpecialization()):new ArrayList<Doctor>());
        if (dto.getFormFillnes()) {
            patientService.updatePatient(authentication, dto.getAppointment());
            return "redirect:/patientProfile";
        } else
            return "patientNewAppointment";
    }

    @GetMapping("/patient/newAppointment")
    public String getNewAppointment(Model model, Authentication authentication) {
        model.addAttribute("dto", new AppointmentDTO().buildAppointment(null, null));
        model.addAttribute("isNewAppointment", true);
        model.addAttribute("listSpecializations", userService.getSpecializationList());
        model.addAttribute("listDoctors", new ArrayList<Doctor>());
        return "patientNewAppointment";
    }

    @PostMapping("/patient/newAppointment/change")
    public String postNewAppointmentChange(@ModelAttribute("dto") AppointmentDTO dto, Model model, Authentication authentication) {
        dto.buildAppointment(dto.getDoctor_id() != null ? doctorService.getById(dto.getDoctor_id()) : null, patientService.getCurrentPatient(authentication));
        model.addAttribute("isNewAppointment", true);
        model.addAttribute("listSpecializations", userService.getSpecializationList());
        model.addAttribute("listDoctors", dto.getSpecialization()!=null?doctorService.getDoctorsList(dto.getSpecialization()):new ArrayList<Doctor>());
        return "patientNewAppointment";
    }

    @PostMapping("/patient/newAppointment")
    public String postNewAppointment(@ModelAttribute("dto") AppointmentDTO dto, Model model, Authentication authentication) {
        dto.buildAppointment(dto.getDoctor_id() != null ? doctorService.getById(dto.getDoctor_id()) : null, patientService.getCurrentPatient(authentication));
        model.addAttribute("isNewAppointment", true);
        model.addAttribute("listSpecializations", userService.getSpecializationList());
        model.addAttribute("listDoctors", dto.getSpecialization()!=null?doctorService.getDoctorsList(dto.getSpecialization()):new ArrayList<Doctor>());
        if (dto.getFormFillnes()) {
            patientService.updatePatient(authentication, dto.getAppointment());
            return "redirect:/patientProfile";
        } else
            return "patientNewAppointment";
    }

    @GetMapping("/patient/history")
    public String getPageAppointmentHistory(Model model, Authentication authentication) {
        model.addAttribute("appointments", patientService.getCurrentPatient(authentication).getAppointments());
        return "patientAppointmentHistory";
    }
}

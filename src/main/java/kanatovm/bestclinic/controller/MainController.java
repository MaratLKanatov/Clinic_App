package kanatovm.bestclinic.controller;

import kanatovm.bestclinic.model.dto.PatientDTO;
import kanatovm.bestclinic.model.enums.Role;
import kanatovm.bestclinic.service.DoctorService;
import kanatovm.bestclinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    private final PatientService patientService;
    private final DoctorService doctorService;

    @Autowired
    public MainController(PatientService patientService, DoctorService doctorService) {
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    @GetMapping("/")
    public String getHomePage(Authentication authentication, Model model) {
        model.addAttribute("authentication", authentication);
        if (authentication == null)
            return "general/index";
        if (authentication.getAuthorities().contains(Role.PATIENT.getAuthoritiesList().get(0)))
            return "redirect:/patientProfile";
        if (authentication.getAuthorities().contains(Role.ADMIN.getAuthoritiesList().get(0)))
            return "redirect:/admin";
        if (authentication.getAuthorities().contains(Role.DOCTOR.getAuthoritiesList().get(0))) {
            doctorService.buildDoctorStatistic(authentication);
            model.addAttribute("statistic", doctorService.buildDoctorStatistic(authentication));
            model.addAttribute("doctor", doctorService.getCurrentDoctor(authentication));
            model.addAttribute("appointments", doctorService.getWaitingAppointments(authentication));
            return "doctorDashboard";
        }
        return "pageHome";
    }

    @GetMapping("/index")
    public String getIndex() {
        return "general/index";
    }

    @GetMapping("/auth/login")
    public String getAuthPage(Authentication authentication, Model model) {
        model.addAttribute("intent", "login");
        if (authentication == null)
            return "pageLogin";
        return "pageHome";
    }

    @GetMapping("/auth/register")
    public String getRegisterPage(Authentication authentication, Model model) {
        model.addAttribute("authentication", authentication);
        model.addAttribute("intent", "reg");
        model.addAttribute("patient", new PatientDTO());
        model.addAttribute("registerStatus", "OK");
        if (authentication == null)
            return "pageLogin";
        return "pageHome";
    }

    @PostMapping("/auth/register")
    public String postRegisterPage(@ModelAttribute("patient") @Valid PatientDTO patient, BindingResult bindingResult, Model model, Authentication authentication) {
        if (!patient.getPassword().equals(patient.getMatchingPassword())) {
            bindingResult.addError(new FieldError("patient", "matchingPassword", "Two passwords' are not equal"));
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("authentication", authentication);
            model.addAttribute("intent", "reg");
            model.addAttribute("registerStatus", "NOT_VALID");
            return "pageLogin";
        }
        patientService.savePatient(patient);
        return "redirect:/auth/login";
    }

    @GetMapping("/test")
    public String testTheApp(Model model) {
        List<String> logs = new ArrayList<>();
        model.addAttribute("logs", logs);
        return "pageTest";
    }

    @GetMapping("/aboutus")
    public String getAboutUs() {
        return "general/about";
    }

    @GetMapping("/services")
    public String getServices() {
        return "general/services";
    }

    @GetMapping("/news")
    public String getNews() {
        return "general/news";
    }

    @GetMapping("/contact")
    public String getContacts() {
        return "general/contact";
    }

    @GetMapping("/elements")
    public String getElements() {
        return "general/elements";
    }
}

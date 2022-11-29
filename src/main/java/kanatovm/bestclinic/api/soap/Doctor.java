package kanatovm.bestclinic.api.soap;

import com.bestsclinic.types.doctor.*;
import kanatovm.bestclinic.service.DoctorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.stream.Collectors;

/**
 * @author Kanatov Marat
 * @package_name kanatovm.bestclinic.api.soap
 * @date 29.11.2022
 */
@Endpoint
public class Doctor {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DoctorService doctorService;

    @ResponsePayload
    @PayloadRoot(namespace = "http://bestsclinic.com/types/calculator", localPart = "PatientCountInput")
    public PatientCountOutput getPatientCount(@RequestPayload PatientCountInput input){
        logger.info("Request received for addition with input "+input);
        ObjectFactory objectFactory = new ObjectFactory();
        PatientCountOutput output = objectFactory.createPatientCountOutput();
        kanatovm.bestclinic.model.entities.Doctor doctor = doctorService.getAllDoctors().stream().filter(doctor1 -> doctor1.getUser().getEmail().equals(input.getEmail())).collect(Collectors.toList()).get(0);
        output.setPatientCount(doctorService.getWaitingAppointments(doctor).size());
        return output;
    }
}

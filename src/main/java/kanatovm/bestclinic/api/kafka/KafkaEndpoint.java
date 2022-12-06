package kanatovm.bestclinic.api.kafka;

import io.swagger.v3.oas.annotations.Operation;
import kanatovm.bestclinic.model.dto.DoctorDTO;
import kanatovm.bestclinic.service.DoctorService;
import kanatovm.bestclinic.utils.kafka.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MessageController.
 *
 * @author Kanatov Marat
 * Date: 01.12.2022
 */
//@RestController
//@RequestMapping("msg")
public class KafkaEndpoint {

    private final KafkaService kafkaService;
    private final DoctorService doctorService;

    @Autowired
    public KafkaEndpoint(
            final KafkaService kafkaService,
            final DoctorService doctorService) {
        this.kafkaService = kafkaService;
        this.doctorService = doctorService;
    }

    @PostMapping
    @Operation(summary = "Send doctor entity as message")
    public void sendMessage(Long doctorId){
        kafkaService.sendMessage(doctorId, DoctorDTO.fromDto(doctorService.getById(doctorId)));
    }
}

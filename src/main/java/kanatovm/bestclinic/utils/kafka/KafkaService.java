package kanatovm.bestclinic.utils.kafka;

import kanatovm.bestclinic.model.dto.DoctorDTO;
import kanatovm.bestclinic.model.entities.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * KafkaService.
 *
 * @author Kanatov Marat
 * Date: 01.12.2022
 */
public class KafkaService {

    @Autowired
    private KafkaTemplate<Long, DoctorDTO> kafkaTemplate;

    public void sendMessage(Long doctorId, DoctorDTO doctor) {
        kafkaTemplate.send("msg", doctorId, doctor);
    }
}

package kanatovm.bestclinic;

import kanatovm.bestclinic.model.dto.DoctorDTO;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@EnableKafka
@SpringBootApplication
public class BestClinicAppApplication {

    @KafkaListener(topics="msg")
    public void msgListener(ConsumerRecord<Long, DoctorDTO> record){
        System.out.println(record.partition());
        System.out.println(record.key());
        System.out.println(record.value());
    }

    public static void main(String[] args) {
        SpringApplication.run(BestClinicAppApplication.class, args);
    }

}

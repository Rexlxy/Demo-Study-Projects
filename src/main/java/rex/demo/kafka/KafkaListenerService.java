package rex.demo.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaListenerService {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @KafkaListener(topics = {"message"})
    public void consume(String message) {
        System.out.println("Received message: " + message);
        this.simpMessagingTemplate.convertAndSend("/topic/notice", message);
    }

}

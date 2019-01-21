package rex.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import rex.demo.kafka.KafkaProducer;
import rex.demo.models.Message;

@Controller
public class MessageController {

    private KafkaProducer kafkaProducer;

    @Autowired
    public MessageController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }


    @GetMapping("/")
    public String index() {
        return "index.html";
    }


    @PostMapping(value = "/message", consumes="application/json", produces="application/json")
    @ResponseBody
    public ResponseEntity<String> publishMessage(@RequestBody Message message) {
        kafkaProducer.sendMessage(message);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }


}

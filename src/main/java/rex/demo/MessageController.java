package rex.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import rex.demo.kafka.KafkaProducer;
import rex.demo.models.Message;

@Controller
public class MessageController {

    private KafkaProducer kafkaProducer;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public MessageController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }


    @GetMapping("/")
    public String index() {
        return "index.html";
    }



    @MessageMapping("/one2one")//@MessageMapping和@RequestMapping功能类似，用于设置URL映射地址，浏览器向服务器发起请求，需要通过该地址。
//    @SendTo("/topic/getResponse")//如果服务器接受到了消息，就会对订阅了@SendTo括号中的地址传送消息。
    public void say(Message message) throws Exception {

        simpMessagingTemplate.convertAndSendToUser(message.getReceiverId(), "/msg", message.getClientId() + ": " + message.getMessage());
//        return "Welcome";
    }
}

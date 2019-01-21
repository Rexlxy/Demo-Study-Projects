package rex.demo.kafka;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import rex.demo.models.Message;

@Component
public class KafkaProducer {

    final public static String MESSSGE_TOPIC = "message";

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    private Gson gson = new GsonBuilder().create();


    public void sendMessage(Message message) {
        String jsonString = gson.toJson(message);
        this.kafkaTemplate.send(MESSSGE_TOPIC, jsonString);
    }

}

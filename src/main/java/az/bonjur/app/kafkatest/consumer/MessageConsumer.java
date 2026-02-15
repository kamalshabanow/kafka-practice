package az.bonjur.app.kafkatest.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {

  @KafkaListener(topics = { "tests"})
  public void myTestTopicListener(String message) {
    System.out.println("Consumed message: " + message);
  }
}
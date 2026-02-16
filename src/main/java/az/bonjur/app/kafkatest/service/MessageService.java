package az.bonjur.app.kafkatest.service;

import az.bonjur.app.kafkatest.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class MessageService {

  private static final Logger log = LoggerFactory.getLogger(MessageService.class);
  private final KafkaTemplate<String, Message> kafkaTemplate;

  public MessageService(KafkaTemplate<String, Message> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public Message sendMessage(
      Message message
  ) {
    if (message.getId() == null || message.getId().isEmpty()) {
      message.setId(UUID.randomUUID().toString());
    }
    if (message.getTimestamp() == null) {
      message.setTimestamp(LocalDateTime.now());
    }

    log.info("Sending message to Kafka: {}", message.getId());

    kafkaTemplate.send("tests", message.getId(), message);

    return message;
  }

  public String test() {
    Message testMessage = new Message(
        UUID.randomUUID().toString(),
        "Test message",
        "Kamal",
        LocalDateTime.now()
    );

    kafkaTemplate.send("tests", testMessage.getId(), testMessage);

    return "Test message sent, check your logs";
  }
}

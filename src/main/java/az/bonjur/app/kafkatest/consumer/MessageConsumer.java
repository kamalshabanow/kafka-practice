package az.bonjur.app.kafkatest.consumer;

import az.bonjur.app.kafkatest.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {


  private static final Logger log = LoggerFactory.getLogger(MessageConsumer.class);

  @KafkaListener(topics = "tests", groupId = "test-group")
  public void consume(
      @Payload Message message,
      @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
      @Header(KafkaHeaders.OFFSET) long offset
  ) {

    log.info("Received message from partition {} offset {}: {}",
        partition, offset, message);

    processMessage(message);
  }

  private void processMessage(Message message) {
    log.info("Processing: {} from {}", message.getContent(), message.getSender());
  }
}
package az.bonjur.app.kafkatest.controller;

import az.bonjur.app.kafkatest.model.Message;
import az.bonjur.app.kafkatest.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/message")
public class MessageController {

  private static final Logger log = LoggerFactory.getLogger(MessageController.class);
  private final MessageService messageService;

  public MessageController(MessageService messageService) {
    this.messageService = messageService;
  }

  @PostMapping
  public ResponseEntity<Message> sendMessage(
      @RequestBody Message message
  ) {
    return ResponseEntity.ok(messageService.sendMessage(message));
  }

  @GetMapping("/test")
  public ResponseEntity<String> test() {

    return ResponseEntity.ok(messageService.test());
  }
}

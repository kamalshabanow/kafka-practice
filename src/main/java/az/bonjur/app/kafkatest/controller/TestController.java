package az.bonjur.app.kafkatest.controller;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tests")
public class TestController {

  private final KafkaTemplate<String, String> kafkaTemplate;

  public TestController(KafkaTemplate<String, String> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  @GetMapping
  public String test() {
    kafkaTemplate.send("tests", "test-key", "test-value");
    return "test";
  }
}
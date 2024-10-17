package org.example.booklibrary.controller;

import jakarta.validation.constraints.Email;
import org.example.booklibrary.service.Impl.KafkaProducerServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/kafka", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class KafkaController {

	private final KafkaProducerServiceImpl kafkaProducerService;

	public KafkaController(KafkaProducerServiceImpl kafkaProducerService) {
		this.kafkaProducerService = kafkaProducerService;
	}

  @GetMapping("/send-simple-message/{requestId}")
  public ResponseEntity<String> sendMessageToKafka(
      @RequestParam("message") String message, @PathVariable String requestId) {
    kafkaProducerService.sendMessage(message, requestId);
    return ResponseEntity.ok("Message sent to Kafka: " + message);
  }
	@GetMapping("/send-email/{requestId}/{email}")
	public ResponseEntity<String> sendEmailMessageToKafka(
					@PathVariable @Email(message = "Invalid email format") String email, @PathVariable String requestId) {
		kafkaProducerService.sendMessageToEmailNoti(email, requestId);
		return ResponseEntity.ok("Message sent to Kafka: " + email);
	}
}

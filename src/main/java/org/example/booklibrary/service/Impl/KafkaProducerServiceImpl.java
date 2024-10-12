package org.example.booklibrary.service.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerServiceImpl {
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerServiceImpl.class);

	@Value("${booklib.kafka.topic}")
	private String TOPIC;

	private final KafkaTemplate<String, String> kafkaTemplate;
	public KafkaProducerServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendMessage(String message, String requestId) {
		kafkaTemplate.send(TOPIC, message);
		LOGGER.info("[Request id {}]: Sent message to kafka: {}", requestId, message);
	}
}

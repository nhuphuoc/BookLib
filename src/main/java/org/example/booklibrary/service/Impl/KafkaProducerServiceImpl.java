package org.example.booklibrary.service.Impl;

import org.example.booklibrary.service.KafkaProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerServiceImpl.class);

	@Value("${booklib.kafka.topic}")
	private String DEFAULT_TOPIC;
	@Value("${booklib.kafka.email_list}")
	private String NOTI_TOPIC;


	private final KafkaTemplate<String, String> kafkaTemplate;
	public KafkaProducerServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendMessage(String message, String requestId) {
		kafkaTemplate.send(DEFAULT_TOPIC, message);
		LOGGER.info("[Request id {}]: Sent message to kafka: {}", requestId, message);
	}
	@Override
	public void sendMessageToEmailNoti(String email, String requestId) {
		kafkaTemplate.send(NOTI_TOPIC, email);
		LOGGER.info("[Request id {}]: Sent message to kafka: {}", requestId, email);
	}
}

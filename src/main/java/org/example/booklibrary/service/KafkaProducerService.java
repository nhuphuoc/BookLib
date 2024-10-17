package org.example.booklibrary.service;

public interface KafkaProducerService {

  void sendMessageToEmailNoti(String email, String requestId);
}

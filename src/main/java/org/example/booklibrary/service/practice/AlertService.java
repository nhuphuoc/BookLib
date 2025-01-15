package org.example.booklibrary.service.practice;

import org.example.booklibrary.entity.practice.Alert;
import org.example.booklibrary.entity.practice.AlertDetails;
import org.example.booklibrary.repository.pratice.AlertDetailsRepository;
import org.example.booklibrary.repository.pratice.AlertGroupRepository;
import org.example.booklibrary.repository.pratice.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AlertService {

	@Autowired
	private AlertRepository alertRepository;

	@Autowired
	private AlertDetailsRepository alertDetailsRepository;

	@Autowired
	private AlertGroupRepository alertGroupRepository;

	public List<Alert> getAllAlerts() {
		return alertRepository.findAll();
	}

	public Optional<Alert> getAlertById(String alertId) {
		return alertRepository.findById(alertId);
	}

	@Transactional
	public Alert createAlert(Alert alert) {
		alert.setAlertId(UUID.randomUUID().toString());
		alert.setAlertDetails(alertDetailsRepository.save(alert.getAlertDetails()));
		alert.setAlertGroups(alert.getAlertGroups().stream()
						.peek(group -> group.setAlert(alert))
						.map(alertGroupRepository::save)
						.collect(Collectors.toList()));
		return alertRepository.save(alert);
	}

	@Transactional
	public Optional<Alert> updateAlert(String alertId, Alert alertDetails) {
		return alertRepository.findById(alertId).map(alert -> {
			alert.setSource(alertDetails.getSource());

			// Cập nhật chi tiết của AlertDetails
			AlertDetails currentAlertDetails = alert.getAlertDetails();
			AlertDetails updatedAlertDetails = alertDetails.getAlertDetails();

			if (currentAlertDetails != null && updatedAlertDetails != null) {
				currentAlertDetails.setPayload(updatedAlertDetails.getPayload());
				alert.setAlertDetails(alertDetailsRepository.save(currentAlertDetails));
			}

			// Cập nhật danh sách AlertGroup
			alert.setAlertGroups(alertDetails.getAlertGroups().stream()
							.peek(group -> group.setAlert(alert))
							.map(alertGroupRepository::save)
							.collect(Collectors.toList()));

			return alertRepository.save(alert);
		});
	}

}


package org.example.booklibrary.service.practice;

import org.example.booklibrary.dto.request.AlertDTO;
import org.example.booklibrary.dto.request.AlertDetailsDTO;
import org.example.booklibrary.dto.request.AlertGroupDTO;
import org.example.booklibrary.entity.practice.Alert;
import org.example.booklibrary.entity.practice.AlertDetails;
import org.example.booklibrary.entity.practice.AlertGroup;
import org.example.booklibrary.repository.pratice.AlertDetailsRepository;
import org.example.booklibrary.repository.pratice.AlertGroupRepository;
import org.example.booklibrary.repository.pratice.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
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
	@Autowired
	private AlertGroupService alertGroupService;

	@Autowired
	private AlertDetailsService alertDetailsService;

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
	public Optional<Alert> updateAlert(String alertId, AlertDTO alertDTO) {
		return alertRepository.findById(alertId).map(alert -> {
			// 1. Cập nhật các trường cơ bản của Alert
			alert.setSource(alertDTO.getSource());

			// 2. Cập nhật AlertDetails
			alertDetailsService.updateAlertDetails(alert.getAlertDetails(), alertDTO.getAlertDetails());

			// 3. Cập nhật danh sách AlertGroup
			List<AlertGroup> updatedGroups = alertGroupService.updateAlertGroups(alert, alertDTO.getAlertGroups());
			alert.setAlertGroups(updatedGroups);

			// 4. Lưu Alert đã cập nhật
			return alertRepository.save(alert);
		});
	}


}


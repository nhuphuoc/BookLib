package org.example.booklibrary.controller;
import org.example.booklibrary.dto.request.AlertDTO;
import org.example.booklibrary.entity.practice.Alert;
import org.example.booklibrary.service.practice.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
public class AlertController {

	@Autowired
	private AlertService alertService;

	@GetMapping
	public List<Alert> getAllAlerts() {
		return alertService.getAllAlerts();
	}

	@GetMapping("/{alertId}")
	public ResponseEntity<Alert> getAlertById(@PathVariable String alertId) {
		return alertService.getAlertById(alertId)
						.map(ResponseEntity::ok)
						.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public Alert createAlert(@RequestBody Alert alert) {
		return alertService.createAlert(alert);
	}

	@PutMapping("/{alertId}")
	public ResponseEntity<Alert> updateAlert(@PathVariable String alertId, @RequestBody AlertDTO alertDetails) {
		return alertService.updateAlert(alertId, alertDetails)
						.map(ResponseEntity::ok)
						.orElseGet(() -> ResponseEntity.notFound().build());
	}
}

package org.example.booklibrary.service.practice;
import java.util.*;
import org.example.booklibrary.dto.request.AlertDetailsDTO;
import org.example.booklibrary.entity.practice.AlertDetails;
import org.example.booklibrary.repository.pratice.AlertDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlertDetailsService {

	@Autowired
	private AlertDetailsRepository alertDetailsRepository;

	/**
	 * Cập nhật AlertDetails theo DTO.
	 * Nếu AlertDetails hiện tại tồn tại, chỉ cập nhật payload.
	 */
	@Transactional
	public void updateAlertDetails(AlertDetails currentDetails, AlertDetailsDTO updatedDetailsDTO) {
		if (currentDetails != null && updatedDetailsDTO != null) {
			currentDetails.setPayload(updatedDetailsDTO.getPayload());
			alertDetailsRepository.save(currentDetails);
		}
	}
}

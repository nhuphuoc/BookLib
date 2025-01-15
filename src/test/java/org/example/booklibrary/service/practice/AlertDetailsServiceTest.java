package org.example.booklibrary.service.practice;

import static org.junit.jupiter.api.Assertions.*;

import org.example.booklibrary.dto.request.AlertDetailsDTO;
import org.example.booklibrary.dto.request.AlertGroupDTO;
import org.example.booklibrary.entity.practice.Alert;
import org.example.booklibrary.entity.practice.AlertDetails;
import org.example.booklibrary.entity.practice.AlertGroup;
import org.example.booklibrary.repository.pratice.AlertDetailsRepository;
import org.example.booklibrary.repository.pratice.AlertGroupRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AlertDetailsServiceTest {

	@InjectMocks
	private AlertDetailsService alertDetailsService;

	@Mock
	private AlertDetailsRepository alertDetailsRepository;

	@Test
	void testUpdateAlertDetails_ShouldUpdatePayloadWhenDetailsExist() {
		// Giả lập AlertDetails hiện tại
		AlertDetails currentDetails = AlertDetails.builder()
						.alertDetailId("1")
						.payload("Old Payload")
						.build();

		// Giả lập AlertDetailsDTO với payload mới
		AlertDetailsDTO updatedDetailsDTO = new AlertDetailsDTO();
		updatedDetailsDTO.setPayload("New Payload");

		// Mock hành vi của repository (save sẽ trả về đối tượng đã cập nhật)
		when(alertDetailsRepository.save(any(AlertDetails.class))).thenAnswer(invocation -> invocation.getArgument(0));

		// Gọi phương thức cần kiểm tra
		alertDetailsService.updateAlertDetails(currentDetails, updatedDetailsDTO);

		// Kiểm tra rằng phương thức save đã được gọi đúng
		verify(alertDetailsRepository, times(1)).save(currentDetails);

		// Kiểm tra rằng payload đã được cập nhật
		assertEquals("New Payload", currentDetails.getPayload());
	}

	@Test
	void testUpdateAlertDetails_ShouldNotUpdateIfDetailsOrDTOIsNull() {
		// Gọi phương thức với AlertDetails null
		alertDetailsService.updateAlertDetails(null, new AlertDetailsDTO());
		// Kiểm tra rằng save không được gọi
		verify(alertDetailsRepository, never()).save(any(AlertDetails.class));

		// Gọi phương thức với DTO null
		AlertDetails currentDetails = AlertDetails.builder().alertDetailId("1").payload("Old Payload").build();
		alertDetailsService.updateAlertDetails(currentDetails, null);
		// Kiểm tra rằng save không được gọi
		verify(alertDetailsRepository, never()).save(any(AlertDetails.class));
	}
}


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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AlertServiceTest {
	@InjectMocks
	private AlertService alertService;

	@Mock
	private AlertRepository alertRepository;

	@Mock
	private AlertDetailsService alertDetailsService;

	@Mock
	private AlertGroupService alertGroupService;

	@Test
	void testUpdateAlert_ShouldUpdateAlertAndRelatedEntities() {
		// Giả lập AlertDTO
		AlertDTO alertDTO = new AlertDTO();
		alertDTO.setSource("Updated Source");

		// Giả lập AlertDetailsDTO
		AlertDetailsDTO alertDetailsDTO = new AlertDetailsDTO();
		alertDetailsDTO.setPayload("Updated Payload");

		alertDTO.setAlertDetails(alertDetailsDTO);

		// Giả lập AlertGroupDTO
		AlertGroupDTO alertGroupDTO1 = new AlertGroupDTO();
		alertGroupDTO1.setGroupName("Updated Group 1");
		AlertGroupDTO alertGroupDTO2 = new AlertGroupDTO();
		alertGroupDTO2.setGroupName("Updated Group 2");
		alertDTO.setAlertGroups(Arrays.asList(alertGroupDTO1, alertGroupDTO2));

		// Giả lập Alert hiện tại trong database
		Alert alert = new Alert();
		alert.setAlertId("1");
		alert.setSource("Old Source");

		AlertDetails currentAlertDetails = new AlertDetails();
		alert.setAlertDetails(currentAlertDetails);

		AlertGroup group1 = new AlertGroup();
		group1.setGroupName("Old Group 1");
		alert.setAlertGroups(Arrays.asList(group1));

		// Mock hành vi của các service
		when(alertRepository.findById("1")).thenReturn(Optional.of(alert));
		doNothing().when(alertDetailsService).updateAlertDetails(any(AlertDetails.class), any(AlertDetailsDTO.class));
		when(alertGroupService.updateAlertGroups(any(Alert.class), anyList())).thenReturn(Arrays.asList(new AlertGroup(), new AlertGroup()));

		// Gọi phương thức cần test
		Optional<Alert> updatedAlert = alertService.updateAlert("1", alertDTO);

		// Kiểm tra kết quả
		assertTrue(updatedAlert.isPresent());
		assertEquals("Updated Source", updatedAlert.get().getSource());

		// Kiểm tra các phương thức service được gọi
		verify(alertRepository, times(1)).findById("1");
		verify(alertDetailsService, times(1)).updateAlertDetails(any(AlertDetails.class), any(AlertDetailsDTO.class));
		verify(alertGroupService, times(1)).updateAlertGroups(eq(alert), anyList());
		verify(alertRepository, times(1)).save(alert);
	}

	@Test
	void testUpdateAlert_ShouldReturnEmptyWhenAlertNotFound() {
		// Mock hành vi không tìm thấy Alert
		when(alertRepository.findById("1")).thenReturn(Optional.empty());

		// Gọi phương thức cần test
		Optional<Alert> updatedAlert = alertService.updateAlert("1", new AlertDTO());

		// Kiểm tra kết quả
		assertFalse(updatedAlert.isPresent());

		// Kiểm tra các phương thức repository không bị gọi thêm sau khi không tìm thấy Alert
		verify(alertRepository, times(1)).findById("1");
		verify(alertDetailsService, never()).updateAlertDetails(any(AlertDetails.class), any(AlertDetailsDTO.class));
		verify(alertGroupService, never()).updateAlertGroups(any(Alert.class), anyList());
		verify(alertRepository, never()).save(any(Alert.class));
	}
}

package org.example.booklibrary.service.practice;

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

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class AlertServiceTest {

	@Mock
	private AlertRepository alertRepository;

	@Mock
	private AlertDetailsRepository alertDetailsRepository;

	@Mock
	private AlertGroupRepository alertGroupRepository;

	@InjectMocks
	private AlertService alertService;

	@Captor
	ArgumentCaptor<Alert> alertCaptor;

//	@BeforeEach
//	public void setUp() {
//		alertService = new AlertService(alertRepository, alertDetailsRepository, alertGroupRepository);
//	}
//
//	@Test
//	public void testUpdateAlertNew() {
//		// Giả lập dữ liệu đầu vào
//		String alertId = "testAlertId";
//		Alert originalAlert = new Alert(alertId, "originalSource", new AlertDetails(), Stream.of(new AlertGroup()).collect(Collectors.toList()));
//		Alert updatedAlert = new Alert(alertId, "updatedSource", new AlertDetails("updatedPayload"), Stream.of(new AlertGroup()).collect(Collectors.toList()));
//
//		when(alertRepository.findById(alertId)).thenReturn(Optional.of(originalAlert));
//		when(alertDetailsRepository.save(any(AlertDetails.class))).thenAnswer(invocation -> invocation.getArgument(0));
//		when(alertGroupRepository.save(any(AlertGroup.class))).thenAnswer(invocation -> invocation.getArgument(0));
//		when(alertRepository.save(any(Alert.class))).thenAnswer(invocation -> invocation.getArgument(0));
//
//		// Test phương thức updateAlertNew
//		Optional<Alert> result = alertService.updateAlertNew(alertId, updatedAlert);
//
//		// Kiểm tra kết quả
//		assertTrue(result.isPresent());
//		assertEquals("updatedSource", result.get().getSource());
//		assertEquals("updatedPayload", result.get().getAlertDetails().getPayload());
//
//		// Kiểm tra các tương tác và cập nhật của AlertGroups
//		assertEquals(1, result.get().getAlertGroups().size());
//		verify(alertGroupRepository, times(1)).save(any(AlertGroup.class));
//		assertEquals(updatedAlert.getAlertGroups().get(0).getGroupName(), result.get().getAlertGroups().get(0).getGroupName());
//	}
}

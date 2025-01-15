package org.example.booklibrary.service.practice;

import org.example.booklibrary.dto.request.AlertGroupDTO;
import org.example.booklibrary.entity.practice.Alert;
import org.example.booklibrary.entity.practice.AlertGroup;
import org.example.booklibrary.repository.pratice.AlertGroupRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlertGroupServiceTest {

	@InjectMocks
	private AlertGroupService alertGroupService;

	@Mock
	private AlertGroupRepository alertGroupRepository;

	private Alert alert;
	private List<AlertGroup> existingGroups;
	private List<AlertGroupDTO> alertGroupDTOs;

	@BeforeEach
	void setUp() {
		// Tạo đối tượng Alert
		alert = new Alert();
		alert.setAlertId("alert-123");

		// Tạo danh sách AlertGroup hiện có
		AlertGroup group1 = AlertGroup.builder()
						.alertGroupId("group-1")
						.groupName("Group 1")
						.eventName("Event 1")
						.alert(alert)
						.build();
		AlertGroup group2 = AlertGroup.builder()
						.alertGroupId("group-2")
						.groupName("Group 2")
						.eventName("Event 2")
						.alert(alert)
						.build();
		existingGroups = List.of(group1, group2);
		alert.setAlertGroups(existingGroups);

		// Tạo danh sách AlertGroupDTO mới
		alertGroupDTOs = List.of(
						new AlertGroupDTO("group-1", "Updated Group 1", "Updated Event 1"),
						new AlertGroupDTO("group-3", "New Group 3", "New Event 3")
		);
	}

	@Test
	void testUpdateAlertGroups_Success() {
		// Mock repository behavior
		when(alertGroupRepository.findById("group-1")).thenReturn(Optional.of(existingGroups.get(0)));
		when(alertGroupRepository.findById("group-3")).thenReturn(Optional.empty());
		when(alertGroupRepository.saveAll(any())).thenAnswer(invocation -> invocation.getArgument(0));

		// Gọi phương thức cần test
		List<AlertGroup> updatedGroups = alertGroupService.updateAlertGroups(alert, alertGroupDTOs);

		// Kiểm tra kết quả
		assertEquals(2, updatedGroups.size());
		assertEquals("Updated Group 1", updatedGroups.get(0).getGroupName());
		assertEquals("Updated Event 1", updatedGroups.get(0).getEventName());
		assertEquals("New Group 3", updatedGroups.get(1).getGroupName());
		assertEquals("New Event 3", updatedGroups.get(1).getEventName());

		// Kiểm tra hành vi repository
		verify(alertGroupRepository).deleteAll(List.of(existingGroups.get(1))); // group-2 bị xóa
		verify(alertGroupRepository).saveAll(updatedGroups);
	}

	@Test
	void testUpdateAlertGroups_EmptyDtoList() {
		// Gọi phương thức cần test với danh sách DTO rỗng
		List<AlertGroup> updatedGroups = alertGroupService.updateAlertGroups(alert, Collections.emptyList());

		// Kiểm tra kết quả
		assertTrue(updatedGroups.isEmpty());
		assertTrue(alert.getAlertGroups().isEmpty());

		// Kiểm tra hành vi repository
		verify(alertGroupRepository).deleteAll(existingGroups);
		verify(alertGroupRepository, never()).saveAll(any());
	}

	@Test
	void testUpdateAlertGroups_NullDtoList() {
		// Gọi phương thức cần test với DTO null
		List<AlertGroup> updatedGroups = alertGroupService.updateAlertGroups(alert, null);

		// Kiểm tra kết quả
		assertTrue(updatedGroups.isEmpty());
		assertTrue(alert.getAlertGroups().isEmpty());

		// Kiểm tra hành vi repository
		verify(alertGroupRepository).deleteAll(existingGroups);
		verify(alertGroupRepository, never()).saveAll(any());
	}

	@Test
	void testUpdateAlertGroups_AddOnlyNewGroups() {
		// Mock repository behavior (không tìm thấy bất kỳ ID nào)
		when(alertGroupRepository.findById(anyString())).thenReturn(Optional.empty());
		when(alertGroupRepository.saveAll(any())).thenAnswer(invocation -> invocation.getArgument(0));

		// Gọi phương thức cần test
		List<AlertGroup> updatedGroups = alertGroupService.updateAlertGroups(alert, alertGroupDTOs);

		// Kiểm tra kết quả
		assertEquals(2, updatedGroups.size());
		assertEquals("New Group 3", updatedGroups.get(1).getGroupName());
		assertEquals("Updated Group 1", updatedGroups.get(0).getGroupName());

	}

}


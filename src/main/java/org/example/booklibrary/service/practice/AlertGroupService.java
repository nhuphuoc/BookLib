package org.example.booklibrary.service.practice;

import org.springframework.stereotype.Service;
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

import java.util.*;
import java.util.stream.Collectors;
@Service
public class AlertGroupService {

	@Autowired
	private AlertGroupRepository alertGroupRepository;

	/**
	 * Cập nhật danh sách AlertGroup theo DTO.
	 * Nếu AlertGroup tồn tại thì cập nhật, nếu chưa có thì tạo mới.
	 */
	@Transactional
	public List<AlertGroup> updateAlertGroups(Alert alert, List<AlertGroupDTO> alertGroupDTOs) {
		if (alertGroupDTOs == null || alertGroupDTOs.isEmpty()) {
			alertGroupRepository.deleteAll(alert.getAlertGroups());
			alert.setAlertGroups(Collections.emptyList());
			return Collections.emptyList();
		}

		// Xóa AlertGroup cũ không có trong danh sách mới
		Set<String> newGroupIds = alertGroupDTOs.stream()
						.map(AlertGroupDTO::getAlertGroupId)
						.filter(Objects::nonNull)
						.collect(Collectors.toSet());

		alertGroupRepository.deleteAll(
						alert.getAlertGroups().stream()
										.filter(group -> !newGroupIds.contains(group.getAlertGroupId()))
										.collect(Collectors.toList())
		);

		// Cập nhật hoặc thêm mới các AlertGroup
		List<AlertGroup> updatedGroups = alertGroupDTOs.stream()
						.map(dto -> alertGroupRepository.findById(dto.getAlertGroupId()).map(existingGroup -> {
							existingGroup.setGroupName(dto.getGroupName());
							existingGroup.setEventName(dto.getEventName());
							existingGroup.setAlert(alert);
							return existingGroup;
						}).orElseGet(() -> AlertGroup.builder()
										.groupName(dto.getGroupName())
										.eventName(dto.getEventName())
										.alert(alert)
										.build()))
						.collect(Collectors.toList());

		// Lưu và trả về danh sách mới
		return alertGroupRepository.saveAll(updatedGroups);
	}

}


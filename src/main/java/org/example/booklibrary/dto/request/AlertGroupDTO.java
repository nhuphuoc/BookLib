package org.example.booklibrary.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlertGroupDTO {
	private String alertGroupId; // null nếu là đối tượng mới
	private String groupName;
	private String eventName;
}

package org.example.booklibrary.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlertDTO {
	private String source;
	private AlertDetailsDTO alertDetails;
	private List<AlertGroupDTO> alertGroups;
}


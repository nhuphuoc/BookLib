package org.example.booklibrary.entity.practice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "alert_group")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AlertGroup {
	@Id
	@Column(name = "alert_group_id")
	@GeneratedValue(strategy = GenerationType.UUID)
	private String alertGroupId;

	@Column
	private String groupName;

	@Column
	private String eventName;

	@ManyToOne
	@JoinColumn(name = "alert_id")
	@JsonIgnore
	private Alert alert;
}

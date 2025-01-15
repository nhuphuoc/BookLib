package org.example.booklibrary.entity.practice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "alert_details")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AlertDetails {
	@Id
	@Column(name = "alert_detail_id")
	private String alertDetailId;

	@Column
	private String payload;

	@OneToOne
	@JoinColumn(name = "alert_id", referencedColumnName = "alert_id")
	@JsonIgnore
	private Alert alert;

}

package org.example.booklibrary.entity.practice;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "alert")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Alert {
	@Id
	@Column(name = "alert_id")
	private String alertId;

	@Column(name = "source")
	private String source;

	@OneToOne(mappedBy = "alert")
	private AlertDetails alertDetails;

	@Builder.Default
	@OneToMany(mappedBy = "alert", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<AlertGroup> alertGroups = new ArrayList<>();

}

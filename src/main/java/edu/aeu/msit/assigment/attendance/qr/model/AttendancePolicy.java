package edu.aeu.msit.assigment.attendance.qr.model;

import java.time.LocalTime;

import edu.aeu.msit.assigment.attendance.qr.model.base.AbstractAuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "attendance-policies")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttendancePolicy extends AbstractAuditableEntity<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4200125778774831721L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	@Column(name = "morning_in_time")
	LocalTime morning_in_time;

	@Column(name = "morning_out_time")
	LocalTime morning_out_time;

	@Column(name = "morning_early_in_time")
	LocalTime morning_early_in_time;

	@Column(name = "morning_late_in_time")
	LocalTime morning_late_in_time;

	@Column(name = "morning_early_out_time")
	LocalTime morning_early_out_time;

	@Column(name = "morning_late_out_time")
	LocalTime morning_late_out_time;
	
	@Column(name = "afternoon_in_time")
	LocalTime afternoon_in_time;
	
	@Column(name = "afternoon_out_time")
	LocalTime afternoon_out_time;

	@Column(name = "afternoon_early_in_time")
	LocalTime afternoon_early_in_time;

	@Column(name = "afternoon_late_in_time")
	LocalTime afternoon_late_in_time;

	@Column(name = "afternoon_early_out_time")
	LocalTime afternoon_early_out_time;

	@Column(name = "afternoon_late_out_time")
	LocalTime afternoon_late_out_time;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "wk_sch_id")
	private WorkingSchedule wkSchedule;
}

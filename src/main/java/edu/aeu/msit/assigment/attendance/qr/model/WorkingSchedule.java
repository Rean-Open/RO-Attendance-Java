package edu.aeu.msit.assigment.attendance.qr.model;

import java.time.LocalTime;

import edu.aeu.msit.assigment.attendance.qr.model.base.AbstractAuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "working-schedules")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkingSchedule extends AbstractAuditableEntity<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1642024460714004048L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	@Column(name = "name")
	String name;
	
	@Column(name = "morning_in_time")
	LocalTime morning_in_time;
	
	@Column(name = "morning_out_time")
	LocalTime morning_out_time;
	
	@Column(name = "afternoon_in_time")
	LocalTime afternoon_in_time;
	
	@Column(name = "afternoon_out_time")
	LocalTime afternoon_out_time;
}

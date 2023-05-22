package edu.aeu.msit.assigment.attendance.qr.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import edu.aeu.msit.assigment.attendance.qr.enums.EAttendanceStatus;
import edu.aeu.msit.assigment.attendance.qr.model.base.AbstractAuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "attendance-logs")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceLog extends AbstractAuditableEntity<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4997159042800279831L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "emp_id")
	private Employee employee;

	@Column(name = "att_log_date")
	private LocalDate date;

	@Column(name = "att_log_morning_in")
	private LocalDateTime morningIn;

	@Column(name = "att_log_morning_out")
	private LocalDateTime morningOut;

	@Enumerated(EnumType.STRING)
	@Column(name = "att_log_morning_status")
	private EAttendanceStatus morningStatus;

	@Column(name = "att_log_afternoon_in")
	private LocalDateTime afternoonIn;

	@Column(name = "att_log_afternoon_out")
	private LocalDateTime afternoonOut;

	@Enumerated(EnumType.STRING)
	@Column(name = "att_log_afternoon_status")
	private EAttendanceStatus afternoonStatus;
}

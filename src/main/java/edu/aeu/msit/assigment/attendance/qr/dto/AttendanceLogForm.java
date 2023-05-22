package edu.aeu.msit.assigment.attendance.qr.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceLogForm {
	Long id;
	Long employeeId;
	LocalDate date;
	LocalDateTime morningIn;
	LocalDateTime morningOut;
	String morningStatus;
	LocalDateTime afternoonIn;
	LocalDateTime afternoonOut;
	String afternoonStatus;
}

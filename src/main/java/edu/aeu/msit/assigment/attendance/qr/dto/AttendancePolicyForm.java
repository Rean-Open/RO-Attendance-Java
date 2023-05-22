package edu.aeu.msit.assigment.attendance.qr.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttendancePolicyForm {
	Long id;
	String morning_in_time;
	String morning_out_time;
	String morning_early_in_time;
	String morning_late_in_time;
	String morning_early_out_time;
	String morning_late_out_time;
	String afternoon_in_time;
	String afternoon_out_time;
	String afternoon_early_in_time;
	String afternoon_late_in_time;
	String afternoon_early_out_time;
	String afternoon_late_out_time;
	Long wkScheduleId;
}

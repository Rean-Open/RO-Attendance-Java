package edu.aeu.msit.assigment.attendance.qr.dto;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkingScheduleForm {
	Long id;
	String name;
	String morning_in_time;
	String morning_out_time;
	String afternoon_in_time;
	String afternoon_out_time;
}

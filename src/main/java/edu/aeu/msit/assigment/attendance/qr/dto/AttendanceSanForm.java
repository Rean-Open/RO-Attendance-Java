package edu.aeu.msit.assigment.attendance.qr.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceSanForm {
	Long empId;
	String empCode;
	String dateTime;
	String attType;
	String qrCodeData;
}

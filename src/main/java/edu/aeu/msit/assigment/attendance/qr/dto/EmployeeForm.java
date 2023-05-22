package edu.aeu.msit.assigment.attendance.qr.dto;

import java.sql.Date;

import edu.aeu.msit.assigment.attendance.qr.enums.EGender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeForm {
	Long id;
	String code;
	String firstName;
	String lastName;
	Date dob;
	String phone;
	EGender gender;
}

package edu.aeu.msit.assigment.attendance.qr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class AssignmentAttendaceQrcodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssignmentAttendaceQrcodeApplication.class, args);
	}

}

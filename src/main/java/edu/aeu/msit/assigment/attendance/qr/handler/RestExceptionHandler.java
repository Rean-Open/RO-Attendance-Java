package edu.aeu.msit.assigment.attendance.qr.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import edu.aeu.msit.assigment.attendance.qr.AssignmentAttendaceQrcodeApplication;
import edu.aeu.msit.assigment.attendance.qr.exception.EmployeeNotFoundException;

import static org.springframework.http.ResponseEntity.notFound;

@RestControllerAdvice(basePackageClasses = { AssignmentAttendaceQrcodeApplication.class })
@Slf4j
public class RestExceptionHandler {

	@ExceptionHandler(value = { EmployeeNotFoundException.class })
	public ResponseEntity  employeeNotFound(EmployeeNotFoundException ex, WebRequest request) {
		log.debug("handling EmployeeNotFoundException...");
		return notFound().build();
	}

}

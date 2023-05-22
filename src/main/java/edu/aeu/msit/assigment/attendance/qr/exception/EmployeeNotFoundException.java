package edu.aeu.msit.assigment.attendance.qr.exception;

public class EmployeeNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -5906296357834357716L;

	public EmployeeNotFoundException() {
	}

	public EmployeeNotFoundException(Long vehicleId) {
		super("Employee: " + vehicleId + " not found.");
	}
}

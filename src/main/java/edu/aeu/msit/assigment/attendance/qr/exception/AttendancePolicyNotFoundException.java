package edu.aeu.msit.assigment.attendance.qr.exception;

public class AttendancePolicyNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8739448010060194097L;

	public AttendancePolicyNotFoundException() {
	}

	public AttendancePolicyNotFoundException(Long id) {
		super("AttendancePolicy: " + id + " not found.");
	}
}

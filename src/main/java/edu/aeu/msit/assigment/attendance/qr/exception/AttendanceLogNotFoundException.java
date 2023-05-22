package edu.aeu.msit.assigment.attendance.qr.exception;

public class AttendanceLogNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4203326711277235625L;

	public AttendanceLogNotFoundException() {
	}

	public AttendanceLogNotFoundException(Long id) {
		super("AttendanceLog: " + id + " not found.");
	}
}

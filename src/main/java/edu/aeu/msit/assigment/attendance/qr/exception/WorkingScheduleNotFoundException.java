package edu.aeu.msit.assigment.attendance.qr.exception;

/**
 * 
 * @author keomorakort.man
 *
 */
public class WorkingScheduleNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1835509390902760471L;

	public WorkingScheduleNotFoundException() {
	}

	public WorkingScheduleNotFoundException(Long id) {
		super("WorkingSchedule: " + id + " not found.");
	}
}

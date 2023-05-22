package edu.aeu.msit.assigment.attendance.qr.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import edu.aeu.msit.assigment.attendance.qr.model.AttendanceLog;
import edu.aeu.msit.assigment.attendance.qr.model.Employee;

public interface AttendanceLogRepository extends JpaRepository<AttendanceLog, Long>, JpaSpecificationExecutor<AttendanceLog> {
	
	List<AttendanceLog> findAllByEmployeeAndDateBetween(Employee employee, LocalDate startDate, LocalDate endDate);
	List<AttendanceLog> findAllByDateBetween(LocalDate startDate, LocalDate endDate);
}

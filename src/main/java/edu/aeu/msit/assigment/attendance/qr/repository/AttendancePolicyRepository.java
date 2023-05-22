package edu.aeu.msit.assigment.attendance.qr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.aeu.msit.assigment.attendance.qr.model.AttendancePolicy;

public interface AttendancePolicyRepository  extends JpaRepository<AttendancePolicy, Long> {

}

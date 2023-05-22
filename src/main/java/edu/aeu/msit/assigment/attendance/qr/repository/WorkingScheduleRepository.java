package edu.aeu.msit.assigment.attendance.qr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.aeu.msit.assigment.attendance.qr.model.WorkingSchedule;

public interface WorkingScheduleRepository extends JpaRepository<WorkingSchedule, Long> {

}

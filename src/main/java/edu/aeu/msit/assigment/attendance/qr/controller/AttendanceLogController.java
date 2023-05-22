package edu.aeu.msit.assigment.attendance.qr.controller;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import edu.aeu.msit.assigment.attendance.qr.dto.AttendanceLogForm;
import edu.aeu.msit.assigment.attendance.qr.dto.AttendanceSanForm;
import edu.aeu.msit.assigment.attendance.qr.enums.EAttendanceStatus;
import edu.aeu.msit.assigment.attendance.qr.exception.AttendanceLogNotFoundException;
import edu.aeu.msit.assigment.attendance.qr.exception.WorkingScheduleNotFoundException;
import edu.aeu.msit.assigment.attendance.qr.model.AttendanceLog;
import edu.aeu.msit.assigment.attendance.qr.model.Employee;
import edu.aeu.msit.assigment.attendance.qr.model.User;
import edu.aeu.msit.assigment.attendance.qr.repository.AttendanceLogRepository;
import edu.aeu.msit.assigment.attendance.qr.repository.EmployeeRepository;
import edu.aeu.msit.assigment.attendance.qr.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/attendance-logs")
@RequiredArgsConstructor
public class AttendanceLogController {
	private final AttendanceLogRepository repository;
	private final EmployeeRepository employeeRepository;
	private final UserRepository userRepository;

	@GetMapping("")
	public ResponseEntity<List<AttendanceLog>> all(@RequestParam(name = "name", required = false) String[] names) {
		return ok(this.repository.findAll());
	}

	@SuppressWarnings("rawtypes")
	@PostMapping("")
	public ResponseEntity save(@RequestBody AttendanceLogForm form, HttpServletRequest request) {
		Employee employee = this.employeeRepository.findById(form.getEmployeeId()).orElseThrow(() -> new WorkingScheduleNotFoundException());
		
		AttendanceLog entity = AttendanceLog.builder()
				.employee(employee)
				.date(LocalDate.now())
				.morningIn(form.getMorningIn())
				.morningOut(form.getMorningOut())
				.morningStatus(EAttendanceStatus.valueOf(form.getMorningStatus()))
				.afternoonIn(form.getAfternoonIn())
				.afternoonOut(form.getAfternoonOut())
				.afternoonStatus(EAttendanceStatus.valueOf(form.getAfternoonStatus()))
			.build();
		AttendanceLog saved = this.repository.save(entity);
		return created(ServletUriComponentsBuilder.fromContextPath(request).path("/api/attendance-policies/{id}")
				.buildAndExpand(saved.getId()).toUri()).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<AttendanceLog> get(@PathVariable("id") Long id) {
		return ok(this.repository.findById(id).orElseThrow(() -> new AttendanceLogNotFoundException()));
	}

	@SuppressWarnings("rawtypes")
	@PutMapping("/{id}")
	public ResponseEntity update(@PathVariable("id") Long id, @RequestBody AttendanceLogForm form, HttpServletRequest request) {
		AttendanceLog existed = this.repository.findById(id).orElseThrow(() -> new AttendanceLogNotFoundException());
		Employee employee = this.employeeRepository.findById(form.getEmployeeId()).orElseThrow(() -> new WorkingScheduleNotFoundException());
		
		existed.setEmployee(employee);
		existed.setDate(LocalDate.now());
		existed.setMorningIn(form.getMorningIn());
		existed.setMorningOut(form.getMorningOut());
		existed.setMorningStatus(EAttendanceStatus.valueOf(form.getMorningStatus()));
		existed.setAfternoonIn(form.getAfternoonIn());
		existed.setAfternoonOut(form.getAfternoonOut());
		existed.setAfternoonStatus(EAttendanceStatus.valueOf(form.getAfternoonStatus()));
		
		this.repository.save(existed);
		return created(ServletUriComponentsBuilder.fromContextPath(request).path("/api/attendance-policies/{id}").buildAndExpand(existed.getId()).toUri()).build();
	}

	@SuppressWarnings("rawtypes")
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		AttendanceLog existed = this.repository.findById(id).orElseThrow(() -> new AttendanceLogNotFoundException());
		this.repository.delete(existed);
		return noContent().build();
	}
	
	@PostMapping("/scan-qr")
	public ResponseEntity<Object> scanQrCode(@AuthenticationPrincipal UserDetails userDetails, @RequestBody AttendanceSanForm form, HttpServletRequest request) throws Exception {
		User user = this.userRepository.findByUsername(userDetails.getUsername()).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
		
		Map<Object, Object> model = new HashMap<>();
		
		return ok(model);
	}
}

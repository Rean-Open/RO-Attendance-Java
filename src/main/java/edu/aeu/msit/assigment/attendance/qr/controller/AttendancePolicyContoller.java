package edu.aeu.msit.assigment.attendance.qr.controller;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

import java.time.LocalTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
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

import edu.aeu.msit.assigment.attendance.qr.dto.AttendancePolicyForm;
import edu.aeu.msit.assigment.attendance.qr.exception.AttendancePolicyNotFoundException;
import edu.aeu.msit.assigment.attendance.qr.exception.WorkingScheduleNotFoundException;
import edu.aeu.msit.assigment.attendance.qr.model.AttendancePolicy;
import edu.aeu.msit.assigment.attendance.qr.model.WorkingSchedule;
import edu.aeu.msit.assigment.attendance.qr.repository.AttendancePolicyRepository;
import edu.aeu.msit.assigment.attendance.qr.repository.WorkingScheduleRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/attendance-policies")
@RequiredArgsConstructor
public class AttendancePolicyContoller {
	
	private final AttendancePolicyRepository repository;
	private final WorkingScheduleRepository wkSchedulerepository;

	@GetMapping("")
	public ResponseEntity<List<AttendancePolicy>> all(@RequestParam(name = "name", required = false) String[] names) {
		return ok(this.repository.findAll());
	}

	@SuppressWarnings("rawtypes")
	@PostMapping("")
	public ResponseEntity save(@RequestBody AttendancePolicyForm form, HttpServletRequest request) {
		AttendancePolicy entity = AttendancePolicy.builder()
				.morning_out_time(LocalTime.parse(form.getMorning_out_time()))
				.morning_early_in_time(LocalTime.parse(form.getMorning_early_in_time()))
				.morning_late_in_time(LocalTime.parse(form.getMorning_late_in_time()))
				.morning_early_out_time(LocalTime.parse(form.getMorning_early_out_time()))
				.morning_late_out_time(LocalTime.parse(form.getMorning_late_out_time()))

				.afternoon_in_time(LocalTime.parse(form.getAfternoon_in_time()))
				.afternoon_out_time(LocalTime.parse(form.getAfternoon_out_time()))
				.afternoon_early_in_time(LocalTime.parse(form.getAfternoon_early_in_time()))
				.afternoon_late_in_time(LocalTime.parse(form.getAfternoon_late_in_time()))
				.afternoon_early_out_time(LocalTime.parse(form.getAfternoon_early_out_time()))
				.afternoon_late_out_time(LocalTime.parse(form.getAfternoon_late_out_time()))
			.build();
		AttendancePolicy saved = this.repository.save(entity);
		return created(ServletUriComponentsBuilder.fromContextPath(request).path("/api/attendance-policies/{id}")
				.buildAndExpand(saved.getId()).toUri()).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<AttendancePolicy> get(@PathVariable("id") Long id) {
		return ok(this.repository.findById(id).orElseThrow(() -> new AttendancePolicyNotFoundException()));
	}

	@SuppressWarnings("rawtypes")
	@PutMapping("/{id}")
	public ResponseEntity update(@PathVariable("id") Long id, @RequestBody AttendancePolicyForm form, HttpServletRequest request) {
		AttendancePolicy existed = this.repository.findById(id).orElseThrow(() -> new AttendancePolicyNotFoundException());
		WorkingSchedule wkSchedule = this.wkSchedulerepository.findById(form.getWkScheduleId()).orElseThrow(() -> new WorkingScheduleNotFoundException());
		
		existed.setMorning_out_time(LocalTime.parse(form.getMorning_out_time()));
		existed.setMorning_early_in_time(LocalTime.parse(form.getMorning_early_in_time()));
		existed.setMorning_late_in_time(LocalTime.parse(form.getMorning_late_in_time()));
		existed.setMorning_early_out_time(LocalTime.parse(form.getMorning_early_out_time()));
		existed.setMorning_late_out_time(LocalTime.parse(form.getMorning_late_out_time()));

		existed.setAfternoon_in_time(LocalTime.parse(form.getAfternoon_in_time()));
		existed.setAfternoon_out_time(LocalTime.parse(form.getAfternoon_out_time()));
		existed.setAfternoon_early_in_time(LocalTime.parse(form.getAfternoon_early_in_time()));
		existed.setAfternoon_late_in_time(LocalTime.parse(form.getAfternoon_late_in_time()));
		existed.setAfternoon_early_out_time(LocalTime.parse(form.getAfternoon_early_out_time()));
		existed.setAfternoon_late_out_time(LocalTime.parse(form.getAfternoon_late_out_time()));
		existed.setWkSchedule(wkSchedule);
		
		this.repository.save(existed);
		return created(ServletUriComponentsBuilder.fromContextPath(request).path("/api/attendance-policies/{id}").buildAndExpand(existed.getId()).toUri()).build();
	}

	@SuppressWarnings("rawtypes")
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		AttendancePolicy existed = this.repository.findById(id).orElseThrow(() -> new AttendancePolicyNotFoundException());
		this.repository.delete(existed);
		return noContent().build();
	}
}

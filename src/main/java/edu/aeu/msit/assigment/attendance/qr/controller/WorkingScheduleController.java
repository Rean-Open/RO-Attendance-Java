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

import edu.aeu.msit.assigment.attendance.qr.dto.WorkingScheduleForm;
import edu.aeu.msit.assigment.attendance.qr.exception.WorkingScheduleNotFoundException;
import edu.aeu.msit.assigment.attendance.qr.model.WorkingSchedule;
import edu.aeu.msit.assigment.attendance.qr.repository.WorkingScheduleRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/working-schedules")
@RequiredArgsConstructor
public class WorkingScheduleController {

	private final WorkingScheduleRepository repository;

	@GetMapping("")
	public ResponseEntity<List<WorkingSchedule>> all(@RequestParam(name = "name", required = false) String[] names) {
		return ok(this.repository.findAll());
	}

	@SuppressWarnings("rawtypes")
	@PostMapping("")
	public ResponseEntity save(@RequestBody WorkingScheduleForm form, HttpServletRequest request) {
		WorkingSchedule entity = WorkingSchedule.builder()
			.name(form.getName())
			.morning_in_time(LocalTime.parse(form.getMorning_in_time()))
			.morning_out_time(LocalTime.parse(form.getAfternoon_out_time()))
			.afternoon_in_time(LocalTime.parse(form.getAfternoon_in_time()))
			.afternoon_out_time(LocalTime.parse(form.getAfternoon_out_time()))
			.build();
		WorkingSchedule saved = this.repository.save(entity);
		return created(ServletUriComponentsBuilder.fromContextPath(request).path("/api/working-schedules/{id}")
				.buildAndExpand(saved.getId()).toUri()).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<WorkingSchedule> get(@PathVariable("id") Long id) {
		return ok(this.repository.findById(id).orElseThrow(() -> new WorkingScheduleNotFoundException()));
	}

	@SuppressWarnings("rawtypes")
	@PutMapping("/{id}")
	public ResponseEntity update(@PathVariable("id") Long id, @RequestBody WorkingScheduleForm form, HttpServletRequest request) {
		WorkingSchedule existed = this.repository.findById(id) .orElseThrow(() -> new WorkingScheduleNotFoundException());
		
		existed.setName(form.getName());
		existed.setMorning_in_time(LocalTime.parse(form.getMorning_in_time()));
		existed.setMorning_out_time(LocalTime.parse(form.getAfternoon_out_time()));
		existed.setAfternoon_in_time(LocalTime.parse(form.getAfternoon_in_time()));
		existed.setAfternoon_out_time(LocalTime.parse(form.getAfternoon_out_time()));

		WorkingSchedule saved = this.repository.save(existed);
		return created(ServletUriComponentsBuilder.fromContextPath(request).path("/api/working-schedules/{id}").buildAndExpand(saved.getId()).toUri()).build();
	}

	@SuppressWarnings("rawtypes")
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		WorkingSchedule existed = this.repository.findById(id).orElseThrow(() -> new WorkingScheduleNotFoundException());
		this.repository.delete(existed);
		return noContent().build();
	}
}

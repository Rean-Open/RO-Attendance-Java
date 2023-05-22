package edu.aeu.msit.assigment.attendance.qr.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.aeu.msit.assigment.attendance.qr.enums.EGender;
import edu.aeu.msit.assigment.attendance.qr.exception.EmployeeNotFoundException;
import edu.aeu.msit.assigment.attendance.qr.model.AttendanceLog;
import edu.aeu.msit.assigment.attendance.qr.model.Employee;
import edu.aeu.msit.assigment.attendance.qr.repository.AttendanceLogRepository;
import edu.aeu.msit.assigment.attendance.qr.repository.AttendancePolicyRepository;
import edu.aeu.msit.assigment.attendance.qr.repository.EmployeeRepository;
import edu.aeu.msit.assigment.attendance.qr.repository.UserRepository;
import edu.aeu.msit.assigment.attendance.qr.repository.WorkingScheduleRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/report")
@RequiredArgsConstructor
public class ReportController {
	private final EmployeeRepository employeeRepository;
	private final UserRepository userRepository;
	private final WorkingScheduleRepository workingScheduleRepository;
	private final AttendancePolicyRepository attendancePolicyRepository;
	private final AttendanceLogRepository attendanceLogRepository;

	@GetMapping("/staff-attendance-detail")
	public ResponseEntity<Map<Object, Object>> staffAttendaceDetail(
			@RequestParam(name = "code", required = true) String code,
			@RequestParam(name = "from_date", required = true) String start_date,
			@RequestParam(name = "end_date", required = true) String end_date) throws Exception {

		Employee employee = this.employeeRepository.findOneByCodeIgnoreCase(code)
				.orElseThrow(() -> new EmployeeNotFoundException());

		List<AttendanceLog> attendances = this.attendanceLogRepository.findAllByEmployeeAndDateBetween(employee,
				LocalDate.parse(start_date), LocalDate.parse(end_date));

		Map<Object, Object> model = new HashMap<>();
		model.put("employee", employee);
		model.put("attendances", attendances);

		return ok(model);
	}

	@GetMapping("/all-staff-attendance-detail")
	public ResponseEntity<Map<Object, Object>> allStaffAttendaceDetail(
			@RequestParam(name = "from_date", required = true) String start_date,
			@RequestParam(name = "end_date", required = true) String end_date) throws Exception {

		List<AttendanceLog> attendances = this.attendanceLogRepository.findAllByDateBetween(LocalDate.parse(start_date),
				LocalDate.parse(end_date));

		Map<Object, Object> model = new HashMap<>();
		model.put("attendances", attendances);
		return ok(model);
	}

	@GetMapping("/staff-list")
	public ResponseEntity<Map<Object, Object>> staffList(@RequestParam(name = "code", required = false) String code,
			@RequestParam(name = "gender", required = false) String gender) throws Exception {

		List<Employee> employees = this.employeeRepository.findAll(new Specification<Employee>() {

			private static final long serialVersionUID = -8622114680509006967L;

			@Override
			public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				if (StringUtils.isNotEmpty(code)) {
					predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("code"), code)));
				}
				if (StringUtils.isNotEmpty(gender)) {
					predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("gender"), EGender.valueOf(gender))));
				}

				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}

		});

		Map<Object, Object> model = new HashMap<>();
		model.put("employees", employees);

		return ok(model);
	}

	@GetMapping("/total-staff-by-gender")
	public ResponseEntity<Map<Object, Object>> totalStaffByGender() throws Exception {

		int totalMale = this.employeeRepository.countByGender(EGender.MALE);
		int totalFemale = this.employeeRepository.countByGender(EGender.FEMALE);

		Map<Object, Object> model = new HashMap<>();
		model.put("total", totalMale + totalFemale);
		model.put("totalMale", totalMale);
		model.put("totalFemale", totalFemale);

		return ok(model);
	}
}

package edu.aeu.msit.assigment.attendance.qr.controller;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
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

import edu.aeu.msit.assigment.attendance.qr.dto.EmployeeForm;
import edu.aeu.msit.assigment.attendance.qr.enums.EGender;
import edu.aeu.msit.assigment.attendance.qr.exception.EmployeeNotFoundException;
import edu.aeu.msit.assigment.attendance.qr.model.Employee;
import edu.aeu.msit.assigment.attendance.qr.repository.EmployeeRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

	private final EmployeeRepository employeeRepository;

	@GetMapping("")
	public ResponseEntity<List<Employee>> all(@RequestParam(name = "code", required = false) String code, @RequestParam(name = "gender", required = false) String gender) {
		
		List<Employee> employees = this.employeeRepository.findAll(new Specification<Employee>() {
			private static final long serialVersionUID = -1546355125822735590L;
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
			}});
		
		return ok(employees);
	}

	@SuppressWarnings("rawtypes")
	@PostMapping("")
	public ResponseEntity save(@RequestBody EmployeeForm form, HttpServletRequest request) {
		Employee saved = this.employeeRepository.save(Employee.builder().firstName(form.getFirstName()).build());
		return created(ServletUriComponentsBuilder.fromContextPath(request).path("/v1/employees/{id}")
				.buildAndExpand(saved.getId()).toUri()).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> get(@PathVariable("id") Long id) {
		return ok(this.employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException()));
	}

	@SuppressWarnings("rawtypes")
	@PutMapping("/{id}")
	public ResponseEntity update(@PathVariable("id") Long id, @RequestBody EmployeeForm form) {
		Employee existed = this.employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException());
		existed.setFirstName(form.getFirstName());

		this.employeeRepository.save(existed);
		return noContent().build();
	}

	@SuppressWarnings("rawtypes")
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		Employee existed = this.employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException());
		this.employeeRepository.delete(existed);
		return noContent().build();
	}
}

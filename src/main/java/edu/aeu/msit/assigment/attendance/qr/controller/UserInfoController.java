package edu.aeu.msit.assigment.attendance.qr.controller;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.aeu.msit.assigment.attendance.qr.model.Employee;
import edu.aeu.msit.assigment.attendance.qr.model.User;
import edu.aeu.msit.assigment.attendance.qr.repository.EmployeeRepository;
import edu.aeu.msit.assigment.attendance.qr.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserInfoController {

	private final UserRepository userRepository;
	private final EmployeeRepository employeeRepository;

	@SuppressWarnings("rawtypes")
	@GetMapping("/me")
	public ResponseEntity currentUser(@AuthenticationPrincipal UserDetails userDetails) throws Exception {
	
		User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(() -> new Exception());
		Employee employee = employeeRepository.findOneByUser(user).orElseThrow(() -> new Exception());
		
		Map<Object, Object> model = new HashMap<>();
		model.put("username", userDetails.getUsername());
		model.put("employee", employee);
		model.put("roles", userDetails.getAuthorities().stream().map(a -> ((GrantedAuthority) a).getAuthority())
				.collect(toList()));
		return ok(model);
	}
}

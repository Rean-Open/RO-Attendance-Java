package edu.aeu.msit.assigment.attendance.qr;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import edu.aeu.msit.assigment.attendance.qr.enums.EAttendanceStatus;
import edu.aeu.msit.assigment.attendance.qr.enums.EGender;
import edu.aeu.msit.assigment.attendance.qr.model.AttendanceLog;
import edu.aeu.msit.assigment.attendance.qr.model.AttendancePolicy;
import edu.aeu.msit.assigment.attendance.qr.model.Employee;
import edu.aeu.msit.assigment.attendance.qr.model.User;
import edu.aeu.msit.assigment.attendance.qr.model.WorkingSchedule;
import edu.aeu.msit.assigment.attendance.qr.repository.AttendanceLogRepository;
import edu.aeu.msit.assigment.attendance.qr.repository.AttendancePolicyRepository;
import edu.aeu.msit.assigment.attendance.qr.repository.EmployeeRepository;
import edu.aeu.msit.assigment.attendance.qr.repository.UserRepository;
import edu.aeu.msit.assigment.attendance.qr.repository.WorkingScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

	private final EmployeeRepository employeeRepository;
	private final UserRepository userRepository;
	private final WorkingScheduleRepository workingScheduleRepository;
	private final AttendancePolicyRepository attendancePolicyRepository;
	private final AttendanceLogRepository attendanceLogRepository;

	private final PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) {
		log.debug("Init Data");
		//this.initUser();
		//this.initEmployee();
		//this.initWorkingSchedule();
		//this.initAttendancePolicy();
		//this.initAttendanceLogs();
	}

	private void initUser() {
		this.userRepository.save(User.builder().username("admin").password(this.passwordEncoder.encode("password"))
				.roles(Arrays.asList("ROLE_USER", "ROLE_ADMIN")).build());

		this.userRepository.save(User.builder().username("morakort").password(this.passwordEncoder.encode("password"))
				.roles(Arrays.asList("ROLE_USER")).build());
		this.userRepository.save(User.builder().username("thida").password(this.passwordEncoder.encode("password"))
				.roles(Arrays.asList("ROLE_USER")).build());
		this.userRepository.save(User.builder().username("ratana").password(this.passwordEncoder.encode("password"))
				.roles(Arrays.asList("ROLE_USER")).build());
		this.userRepository.save(User.builder().username("pengleng").password(this.passwordEncoder.encode("password"))
				.roles(Arrays.asList("ROLE_USER")).build());

		log.debug("printing all users...");
		this.userRepository.findAll().forEach(v -> log.debug(" User :" + v.toString()));
	}

	private void initEmployee() {
		User user1 = this.userRepository.getReferenceById(2L);
		User user2 = this.userRepository.getReferenceById(3L);
		User user3 = this.userRepository.getReferenceById(4L);
		User user4 = this.userRepository.getReferenceById(5L);

		this.employeeRepository.save(Employee.builder().code("EMP-00001").firstName("Keomorakort").lastName("Man")
				.email("mankeomorakort@gmail.com").phoneNumber("012996809").gender(EGender.MALE).user(user1).build());

		this.employeeRepository.save(Employee.builder().code("EMP-00002").firstName("Thida").lastName("Tek")
				.email("thida.tek@gmail.com").phoneNumber("012996808").gender(EGender.FEMALE).user(user2).build());

		this.employeeRepository.save(Employee.builder().code("EMP-00003").firstName("Ratana").lastName("Sok")
				.email("sok.ratana@gmail.com").phoneNumber("012996807").gender(EGender.MALE).user(user3).build());

		this.employeeRepository.save(Employee.builder().code("EMP-00004").firstName("Pengleng").lastName("Huot")
				.email("huot.pengleng@gmail.com").phoneNumber("012996806").gender(EGender.MALE).user(user4).build());

		// this.employeeRepository.findAll().forEach(v -> log.debug(" Employees :" +
		// v.toString()));
	}

	private void initWorkingSchedule() {
		WorkingSchedule schedule = WorkingSchedule.builder().name("Full Time Monday-Friday")
				.morning_in_time(LocalTime.of(8, 0)).morning_out_time(LocalTime.of(12, 0))
				.afternoon_in_time(LocalTime.of(13, 0)).afternoon_out_time(LocalTime.of(17, 0)).build();
		this.workingScheduleRepository.save(schedule);

	}

	private void initAttendancePolicy() {
		WorkingSchedule schedule = this.workingScheduleRepository.getReferenceById(1L);

		AttendancePolicy policy = AttendancePolicy.builder().morning_in_time(LocalTime.of(8, 0))
				.morning_out_time(LocalTime.of(12, 0)).morning_early_in_time(LocalTime.of(7, 0))
				.morning_late_in_time(LocalTime.of(8, 15)).morning_early_out_time(LocalTime.of(12, 0))
				.morning_late_out_time(LocalTime.of(12, 30))

				.afternoon_in_time(LocalTime.of(13, 0)).afternoon_out_time(LocalTime.of(17, 0))
				.afternoon_early_in_time(LocalTime.of(12, 30)).afternoon_late_in_time(LocalTime.of(13, 15))
				.afternoon_early_out_time(LocalTime.of(17, 0)).afternoon_late_out_time(LocalTime.of(19, 0))
				.wkSchedule(schedule).build();
		this.attendancePolicyRepository.save(policy);
	}

	private void initAttendanceLogs() {
		List<Employee> employees = this.employeeRepository.findAll();
		Calendar cal = Calendar.getInstance();
		Integer year = 2023;
		List<Integer> months = Arrays.asList(1, 2, 3, 4);

		for (Integer month : months) {

			cal.clear();
			cal.set(year, month - 1, 1);
			int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

			for (int day = 1; day < daysInMonth; day++) {
				for (Employee employee : employees) {
					AttendanceLog entity = AttendanceLog.builder().employee(employee)
							.date(LocalDate.of(year, month, day))
							.morningIn(LocalDateTime.of(year, month, day, 7, 30, 00))
							.morningOut(LocalDateTime.of(year, month, day, 12, 00, 00))
							.morningStatus(EAttendanceStatus.PRESENT)
							.afternoonIn(LocalDateTime.of(year, month, day, 13, 30, 00))
							.afternoonOut(LocalDateTime.of(year, month, day, 17, 30, 00))
							.afternoonStatus(EAttendanceStatus.PRESENT).build();
					this.attendanceLogRepository.save(entity);
				}
			}
		}
	}
}

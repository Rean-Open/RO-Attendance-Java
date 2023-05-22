package edu.aeu.msit.assigment.attendance.qr.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import edu.aeu.msit.assigment.attendance.qr.enums.EGender;
import edu.aeu.msit.assigment.attendance.qr.model.Employee;
import edu.aeu.msit.assigment.attendance.qr.model.User;

@RepositoryRestResource(path = "employees", collectionResourceRel = "employees", itemResourceRel = "employee")
public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

	Optional<Employee> findOneByCodeIgnoreCase(String code);
	
	Optional<Employee> findOneByUser(User user);

	List<Employee> findAllByCodeAndGender(String code, EGender gender);

	int countByGender(EGender gender);
	
}

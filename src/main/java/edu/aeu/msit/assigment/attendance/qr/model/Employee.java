package edu.aeu.msit.assigment.attendance.qr.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.aeu.msit.assigment.attendance.qr.enums.EGender;
import edu.aeu.msit.assigment.attendance.qr.model.base.AbstractAuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employees")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends AbstractAuditableEntity<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4119590209352919450L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	@NotEmpty
	String code;
	
	@Size(max = 100)
	@Column(name = "first_name")
	private String firstName;

	@Size(max = 100)
	@Column(name = "last_name")
	private String lastName;
	
	@Size(max = 100)
	@Column(name = "first_name_kh")
	private String firstNameKh;

	@Size(max = 100)
	@Column(name = "last_name_kh")
	private String lastNameKh;

	@Column(name = "birth_date")
	private LocalDate birthDate;

	@Size(max = 20)
	@Column(name = "phone_number")
	private String phoneNumber;

	@Enumerated(EnumType.STRING)
	EGender gender;

	@Email
	@Size(max = 150)
	@Column(name = "email")
	private String email;

	@Size(max = 100)
	@Column(name = "nationality")
	private String nationality;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;
}

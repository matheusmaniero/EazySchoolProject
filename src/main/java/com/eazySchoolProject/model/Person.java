package com.eazySchoolProject.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.eazySchoolProject.annotation.FieldsValueMatch;
import com.eazySchoolProject.annotation.PasswordValidator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@FieldsValueMatch.List({
	@FieldsValueMatch(
			field = "pwd",
			fieldMatch = "confirmPwd",
			message = "Passwords dont match!"
			
			
			),
	@FieldsValueMatch(
			field = "email",
			fieldMatch = "confirmEmail",
			message = "Email addresses dont match!"
			
			)
	
})
public class Person extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator ="native")
	@GenericGenerator(name ="native",strategy = "native")
	private int personId;
	
	@NotBlank(message="Name must not be blank")
	@Size(min=3, message="Name must be at least 3 characters")
	private String name;
	
	@NotBlank(message="Mobile number must not be blank")
	@Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
	private String mobileNumber;
	
	@NotBlank(message="Email must not be blank")
	@Email(message="Please provide a valid email address")
	private String email;
	
	@NotBlank(message="Confirm Email not be blank")
	@Email(message="Please provide a valid confirmation")	
	@Transient
	private String confirmEmail;
	
	@NotBlank(message="Password must not be blank")
	@Size(min=5, message="Password must be at least 5 characters")
	@PasswordValidator
	private String pwd;
	
	@NotBlank(message="Confirm Password must not be blank")
	@Size(min=5, message="Confirm Password must be at least 5 characters")
	@Transient
	private String confirmPwd;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, targetEntity = Roles.class)
	@JoinColumn(name = "role_id", referencedColumnName = "roleId", nullable = false)
	private Roles roles;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Address.class)
	@JoinColumn(name = "address_id", referencedColumnName = "addressId", nullable = true)
	private Address address;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true )
	@JoinColumn(name = "class_id", referencedColumnName = "classId", nullable = true)
	private EazyClass eazyClass;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable (name = "person_courses", joinColumns = {@JoinColumn(name = "person_id", referencedColumnName = "personId")},
	inverseJoinColumns = { @JoinColumn(name="course_id", referencedColumnName = "courseId")})
	private Set<Courses> courses;
	
	
	
}

package com.eazySchoolProject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.eazySchoolProject.annotation.FieldsValueMatch;
import com.eazySchoolProject.annotation.PasswordValidator;

import lombok.Data;

@Data
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
	
	//private Roles roles;
	
	//private Address address;
	
	
	
}

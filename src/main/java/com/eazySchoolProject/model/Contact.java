package com.eazySchoolProject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name="contact_msg")
@NamedQueries({
	@NamedQuery(name="Contact.findOpenMsgs",
			query = "SELECT c FROM Contact c WHERE c.status=:status"),
	@NamedQuery(name="Contact.updateMsgStatus",
			query = "UPDATE Contact c SET c.status = ?1 WHERE c.contactId=?2")
})
public class Contact extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	private int contactId;
	
	private String status;
	
	@NotBlank(message = "Name must not be blank")
	@Size (min=3, message = "Name must be at least 3 characters")
	private String name;
	
	@NotBlank(message = "Mobile number must not be blank")
	@Pattern(regexp="(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
	private String mobileNum;
	
	@NotBlank (message = "Email must not be blank")
	@Email (message = "Please provide a valid email address")
	private String email;
	
	@NotBlank(message = "Subject must not be blank")
	@Size(min =5, message = "Subject must be at least 5 characters")
	private String subject;
	@NotBlank(message = "Message must not be blank")
	@Size(min = 10, message = "Message must be at least 10 characters")
	private String message;

}

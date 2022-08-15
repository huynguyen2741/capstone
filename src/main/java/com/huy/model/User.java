package com.huy.model;

//import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="User")
public class User {
	@Id
	@GeneratedValue
	@Column(name="userId")
	private int userId;
	@Column(name="firstName")
	private String firstName;
	@Column(name="lastName")
	private String lastName;
	@Column(name="email")
	private String email;
	@Column(name="userName")
	private String userName;
	@Column(name="password")
	private String password;
	@Column(name="contact")
	private String contact;
	@Column(name="SSN")
	private String SSN;
	@Column(name="roles")
	private String roles;
	@OneToMany(mappedBy="user")
	
	private List<Address> addresses;
	
	
//	  @ManyToMany(mappedBy="users")	  
//	  
//	  private List<Role> roles;
	 
	
	@OneToMany(mappedBy="user")
	
	private List<Order> orders;
	
	public User(String firstName, String lastName,String email, String userName,String password, String contact, String SSN, String roles) {
		this.firstName = firstName;
		this.lastName = lastName;		
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.contact = contact;
		this.password = SSN;
		this.roles=roles;
	}

	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	
}

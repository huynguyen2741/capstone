package com.huy.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Role")
public class Role {
	@Id
	@GeneratedValue
	@Column(name="roleId")
	private int roleId;
	@Column(name="role")
	private String role;
	
	@ManyToMany
	@JoinTable(name="USER_ROLE",
		joinColumns= {@JoinColumn(name="userId")},
		inverseJoinColumns= {@JoinColumn(name="roleId")}
	)
	private List<User> users;
	
	public Role(String role) {
		this.role = role;
	}
	
	
}

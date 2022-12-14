package com.huy.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huy.model.User;

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
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="roleId")
	private int roleId;
	@Column(name="role")
	private String role;
	
	@JsonIgnore
	@ManyToMany(mappedBy="roles")  //bi-directional
    private List<User> users = new ArrayList<User>();
	 
	
	public Role(String role) {
		this.role = role;
	}
	
	
}

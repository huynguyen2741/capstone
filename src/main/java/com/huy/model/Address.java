package com.huy.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Address")
public class Address {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="addressId")
	private int addressId;	
	@Column(name="city")
	private String city;
	@Column(name="state")
	private String state;
	@Column(name="street")
	private String street;
	@Column(name="zipcode")
	private String zipcode;
	@Column(name="country")
	private String country;
	@Column(name="aptNumber")
	private String aptNumber;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;


	public Address(String city, String state, String street, String zipcode, String country, String aptNumber,
			User user) {
		super();
		this.city = city;
		this.state = state;
		this.street = street;
		this.zipcode = zipcode;
		this.country = country;
		this.aptNumber = aptNumber;
		this.user = user;
	}
	

}

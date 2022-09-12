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
//@AllArgsConstructor
//@NoArgsConstructor
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
	
	public Address() {
		super();
	}

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


	public int getAddressId() {
		return addressId;
	}


	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getZipcode() {
		return zipcode;
	}


	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getAptNumber() {
		return aptNumber;
	}


	public void setAptNumber(String aptNumber) {
		this.aptNumber = aptNumber;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	

}

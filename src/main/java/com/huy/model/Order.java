package com.huy.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Order")
public class Order {
	@Id
	@GeneratedValue
	@Column(name="orderId")
	private int orderId;
	@Column(name="amount")
	private double amount;
	@Column(name="date")
	private String date;
	@Column(name="status")
	private String status;
	@Column(name="billingAdd")
	private String billingAdd;	
	@Column(name="shippingAdd")
	private String shippingAdd;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="op_id")
	private OrderProduct op;
	

	public Order(double amount, String date, String status, String billingAdd, String shippingAdd) {
		super();
		this.amount = amount;
		this.date = date;
		this.status = status;
		this.billingAdd = billingAdd;
		this.shippingAdd = shippingAdd;
	}
	
}


package com.huy.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Table(name="Ord")
public class Order {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	
	@OneToMany (mappedBy="order",cascade=CascadeType.ALL)
	private List<OrderProduct> ops;

	public Order() {
		super();
	}
	
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBillingAdd() {
		return billingAdd;
	}

	public void setBillingAdd(String billingAdd) {
		this.billingAdd = billingAdd;
	}

	public String getShippingAdd() {
		return shippingAdd;
	}

	public void setShippingAdd(String shippingAdd) {
		this.shippingAdd = shippingAdd;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderProduct> getOps() {
		return ops;
	}

	public void setOps(List<OrderProduct> ops) {
		this.ops = ops;
	}

	public Order(int orderId, double amount, String date, String status, String billingAdd, String shippingAdd,
			User user, List<OrderProduct> ops) {
		super();
		this.orderId = orderId;
		this.amount = amount;
		this.date = date;
		this.status = status;
		this.billingAdd = billingAdd;
		this.shippingAdd = shippingAdd;
		this.user = user;
		this.ops = ops;
	}
	
	
}

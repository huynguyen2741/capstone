
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
@AllArgsConstructor
@NoArgsConstructor
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
	
	
}

package com.huy.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="OrderProduct")
public class OrderProduct {
	@Id
	@GeneratedValue
	@Column(name="op_id")
	private int op_id;
	@Column(name="order_quantity")
	private int order_quantity;
	
	@OneToMany(mappedBy="op")
	private List<Order> orders;
	
	@OneToMany(mappedBy="op")
	private List<Product> products;
}

package com.huy.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Table(name="OrderProduct")
public class OrderProduct {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="op_id")
	private int op_id;
	@Column(name="order_quantity")
	private int order_quantity;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="orderId")
	private Order order;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="productId")
	private Product product;

	public OrderProduct() {
		super();
	}
	
	public int getOp_id() {
		return op_id;
	}

	public void setOp_id(int op_id) {
		this.op_id = op_id;
	}

	public int getOrder_quantity() {
		return order_quantity;
	}

	public void setOrder_quantity(int order_quantity) {
		this.order_quantity = order_quantity;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public OrderProduct(int op_id, int order_quantity, Order order, Product product) {
		super();
		this.op_id = op_id;
		this.order_quantity = order_quantity;
		this.order = order;
		this.product = product;
	}
	
}

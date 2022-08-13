package com.huy.model;

import java.util.List;
import com.huy.model.ProductCategory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Product")
public class Product {
	@Id
	@GeneratedValue
	@Column(name="productId")
	private int productId;
	@Column(name="name")
	private String name;
	@Column(name="description")
	private String description;
	@Column(name="price")
	private double price;
	@Column(name="weight")
	private double weight;
	@Column(name="quantity")
	private int quantity;	
	@Column(name="image_url")
	private String image_url;
	
	@ManyToOne
	@JoinColumn(name="op_id")
	private OrderProduct op;
	
	@OneToOne
	@JoinColumn(name="categoryId")
	private ProductCategory productCategory;
	
	
	public Product(String name, String description, double price, double weight, int quantity, String image_url) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.weight = weight;
		this.quantity = quantity;
		this.image_url = image_url;
	}
	
	
}

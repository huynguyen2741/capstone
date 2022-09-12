package com.huy.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huy.model.ProductCategory;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Table(name="Product")
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	
	@OneToMany (mappedBy="order",cascade=CascadeType.ALL)
	private List<OrderProduct> ops;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="categoryId")
	private ProductCategory productCategory;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public List<OrderProduct> getOps() {
		return ops;
	}

	public void setOps(List<OrderProduct> ops) {
		this.ops = ops;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public Product(int productId, String name, String description, double price, double weight, int quantity,
			String image_url, List<OrderProduct> ops, ProductCategory productCategory) {
		super();
		this.productId = productId;
		this.name = name;
		this.description = description;
		this.price = price;
		this.weight = weight;
		this.quantity = quantity;
		this.image_url = image_url;
		this.ops = ops;
		this.productCategory = productCategory;
	}
	
	
}

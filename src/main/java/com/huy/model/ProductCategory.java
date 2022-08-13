package com.huy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="ProductCategory")
public class ProductCategory {
	@Id
	@GeneratedValue
	@Column(name="categoryId")
	private int categoryId;
	@Column(name="category")
	private String category;
	@OneToOne
	private Product product;
	
	public ProductCategory(String category) {
		super();
		this.category = category;
	}
	
	
}

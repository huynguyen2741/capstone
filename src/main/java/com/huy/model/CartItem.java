package com.huy.model;

import java.util.Map;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
	
//	private Product product;
	private int id;
	private int inventory;
	private double price;
	private int quantity;
}

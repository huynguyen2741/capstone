package com.huy.model;

import java.util.Map;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class CartItem {
	
//	private Product product;
	private int id;
	private int inventory;
	private double price;
	private int quantity;
	
	public CartItem(){
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getInventory() {
		return inventory;
	}
	public void setInventory(int inventory) {
		this.inventory = inventory;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public CartItem(int id, int inventory, double price, int quantity) {
		super();
		this.id = id;
		this.inventory = inventory;
		this.price = price;
		this.quantity = quantity;
	}
	
	
}

package com.huy.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@SessionScope
public class Cart {

	private double total_amount;
	private List<CartItem> items = new ArrayList<>();
//	private HashMap<CartItem,Integer> items = new HashMap<>();
	double cartTotal = 0;
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		result.append("Cart Info\n\nItem\t\tQuantity\t\tPrice\t\tTotal\n");
		items.stream()
			.forEach(i-> {
				double itemTotal = i.getQuantity() * i.getPrice();
				result.append(i.getId() + "\t\t\t" + i.getQuantity() + "\t\t\t\t" + i.getPrice() + "\t\t" + itemTotal+"\n");
		});
		result.append(getTotal(items));
		return result.toString();
	}
	
	public String getTotal(List<CartItem> items) {
		String grandTotal = "\n\n\t\t\t\t\t\t\tGrand Total: $" ; 
		items.stream()
		.forEach(i-> {
			double itemTotal = i.getQuantity() * i.getPrice();
			cartTotal += itemTotal;
			}
		);
		return grandTotal+cartTotal;
	}
}

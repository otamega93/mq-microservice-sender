package com.example.mqmicroservicesender.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CompletedOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String productOne;
	
	private String productTwo;
	
	private Double total;

	public CompletedOrder(Long id, String productOne, String productTwo, Double total) {
		super();
		this.id = id;
		this.productOne = productOne;
		this.productTwo = productTwo;
		this.total = total;
	}

	public CompletedOrder() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductOne() {
		return productOne;
	}

	public void setProductOne(String productOne) {
		this.productOne = productOne;
	}

	public String getProductTwo() {
		return productTwo;
	}

	public void setProductTwo(String productTwo) {
		this.productTwo = productTwo;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "CompletedOrder [id=" + id + ", productOne=" + productOne + ", productTwo=" + productTwo + ", total=" + total
				+ "]";
	}
		
}

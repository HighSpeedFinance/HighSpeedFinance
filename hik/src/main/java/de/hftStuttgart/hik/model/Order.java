package de.hftStuttgart.hik.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int orderAmount;
	private float orderSumm;
	private String orderNumber;
	private Status status;
	
	public Order(){
		
	}

	public Order(Long id, int orderAmount, float orderSumm, String orderNumber, Status status) {
		super();
		this.id = id;
		this.orderAmount = orderAmount;
		this.orderSumm = orderSumm;
		this.orderNumber = orderNumber;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}

	public float getOrderSumm() {
		return orderSumm;
	}

	public void setOrderSumm(float orderSumm) {
		this.orderSumm = orderSumm;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	

}

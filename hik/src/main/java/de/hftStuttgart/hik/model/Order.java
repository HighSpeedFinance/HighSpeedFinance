package de.hftStuttgart.hik.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date date;
	private int supId;
	private Status status;
	private int itemNumb;
	private String description;
	private double unitPrice;
	private long amount;
	private double sum;

	public Order() {

	}

	public Order(final Long id, final Date date, final int supId, final Status status, final int itemNumb,
			final String description, final double unitPrice, final long amount, final double sum) {

		this.id = id;
		this.date = date;
		this.supId = supId;
		this.status = status;
		this.itemNumb = itemNumb;
		this.description = description;
		this.unitPrice = unitPrice;
		this.amount = amount;
		this.sum = sum;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getSupId() {
		return supId;
	}

	public void setSupId(int supId) {
		this.supId = supId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getItemNumb() {
		return itemNumb;
	}

	public void setItemNumb(int itemNumb) {
		this.itemNumb = itemNumb;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}
	
	

}

package de.hftStuttgart.hik.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int invoiceNumber;
	private int cusId;
	private Status status;
	private Date date;
	private double sum;

	public Invoice() {

	}

	public Invoice(final Long id, final int invoiceNumber, final int cusId, final Status status, final Date date,
			final double sum) {
		this.id = id;
		this.invoiceNumber = invoiceNumber;
		this.cusId = cusId;
		this.status = status;
		this.date = date;
		this.sum = sum;
	}

}

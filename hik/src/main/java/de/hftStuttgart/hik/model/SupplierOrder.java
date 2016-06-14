package de.hftStuttgart.hik.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

// TODO: Auto-generated Javadoc
/**
 * The Class SupplierOrder.
 */
@Entity
public class SupplierOrder {
	
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/** The order number. */
	private int orderNumber;
	
	/** The date. */
	private String date;
	
	/** The sup id. */
	private int supId;
	
	/** The status. */
	private Status status;
	
	/** The item numb. */
	private int itemNumb;
	
	/** The description. */
	private String description;
	
	/** The unit price. */
	private double unitPrice;
	
	/** The amount. */
	private int amount;
	
	/** The sum price. */
	private double sumPrice;
	
	/** The tax. */
	private double tax;

	/** The supplier. */
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Supplier.class)
	private Supplier supplier;

	/**
	 * Instantiates a new supplier order.
	 */
	public SupplierOrder() {

	}

	/**
	 * Instantiates a new supplier order.
	 *
	 * @param date the date
	 * @param order_nr the order_nr
	 * @param status the status
	 * @param itemNumb the item numb
	 * @param description the description
	 * @param unitPrice the unit price
	 * @param amount the amount
	 * @param tax the tax
	 */
	public SupplierOrder(final String date, final int order_nr,  final Status status, final int itemNumb,
			final String description, final double unitPrice, final int amount, final double tax) {

		this.date = date;
		this.orderNumber = order_nr;
		this.status = status;
		this.itemNumb = itemNumb;
		this.description = description;
		this.unitPrice = unitPrice;
		this.amount = amount;
		this.sumPrice = (unitPrice * ((tax/100)+1)) * amount;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Gets the sup id.
	 *
	 * @return the sup id
	 */
	public int getSupId() {
		return supId;
	}

	/**
	 * Sets the sup id.
	 *
	 * @param supId the new sup id
	 */
	public void setSupId(int supId) {
		this.supId = supId;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}
	
	/**
	 * Gets the status string.
	 *
	 * @return the status string
	 */
	public String getStatusString(){
		switch(this.status.toString()){
		case "SUCCEEDED": return "Bezahlt";
		case "PENDING": return "Offen";
		case "ENABLED" : return "Freigegeben";
		default: return " ";
		}
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * Gets the item numb.
	 *
	 * @return the item numb
	 */
	public int getItemNumb() {
		return itemNumb;
	}

	/**
	 * Sets the item numb.
	 *
	 * @param itemNumb the new item numb
	 */
	public void setItemNumb(int itemNumb) {
		this.itemNumb = itemNumb;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the unit price.
	 *
	 * @return the unit price
	 */
	public double getUnitPrice() {
		return unitPrice;
	}

	/**
	 * Sets the unit price.
	 *
	 * @param unitPrice the new unit price
	 */
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * Sets the amount.
	 *
	 * @param amount the new amount
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	/**
	 * Gets the supplier id.
	 *
	 * @return the supplier id
	 */
	public long getSupplierId() {
		return supplier.getId();
	}

	/**
	 * Gets the supplier.
	 *
	 * @return the supplier
	 */
	public String getSupplier() {
		return supplier.getSupplierCompanyName();
	}

	/**
	 * Gets the supplier object.
	 *
	 * @return the supplier object
	 */
	public Supplier getSupplierObject() {
		return supplier;
	}

	/**
	 * Sets the supplier.
	 *
	 * @param supplier the new supplier
	 */
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	/**
	 * Gets the order number.
	 *
	 * @return the order number
	 */
	public int getOrderNumber() {
		return orderNumber;
	}

	/**
	 * Sets the order number.
	 *
	 * @param orderNumber the new order number
	 */
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	/**
	 * Gets the sum price.
	 *
	 * @return the sum price
	 */
	public double getSumPrice() {
		return Math.round(100.0 * sumPrice) / 100.0;
	}

	/**
	 * Sets the sum price.
	 *
	 * @param sumPrice the new sum price
	 */
	public void setSumPrice(double sumPrice) {
		this.sumPrice = sumPrice;
	}
	
	/**
	 * Gets the tax.
	 *
	 * @return the tax
	 */
	public double getTax() {
		return tax;
	}

	/**
	 * Sets the tax.
	 *
	 * @param tax the new tax
	 */
	public void setTax(double tax) {
		this.tax = tax;
		this.sumPrice = (this.unitPrice * ((tax/100)+1)) * this.amount;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.valueOf(Math.round(100.0 * sumPrice) / 100.0);
	}	
	
	
}

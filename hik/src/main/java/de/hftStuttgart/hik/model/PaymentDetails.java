package de.hftStuttgart.hik.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

// TODO: Auto-generated Javadoc
/**
 * The Class PaymentDetails. The Class PaymentDetails Functions as template for
 * the Database and shows relationships between the Class PaymentDetails and
 * Supplier
 */
@Entity
public class PaymentDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String accountOwner;

	private String bic;

	private String iban;

	private String creditInstitution;

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "supplierPaymentDetails")
	private Supplier owner;

	/**
	 * Instantiates a new payment detail.
	 */
	public PaymentDetails() {

	}

	/**
	 * Instantiates a new payment detail.
	 *
	 * @param accountOwner
	 *            the account owner
	 * @param bic
	 *            the bic
	 * @param iban
	 *            the iban
	 * @param creditInstitution
	 *            the credit institution
	 */
	public PaymentDetails(final String accountOwner, final String bic, final String iban,
			final String creditInstitution) {

		this.accountOwner = accountOwner;
		this.bic = bic;
		this.iban = iban;
		this.creditInstitution = creditInstitution;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountOwner() {
		return accountOwner;
	}

	public void setAccountOwner(String accountOwner) {
		this.accountOwner = accountOwner;
	}

	public String getBic() {
		return bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getCreditInstitution() {
		return creditInstitution;
	}

	public void setCreditInstitution(String creditInstitution) {
		this.creditInstitution = creditInstitution;
	}

}

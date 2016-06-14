package de.hftStuttgart.hik.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

// TODO: Auto-generated Javadoc
/**
 * The Class PaymentDetails.
 */
@Entity
public class PaymentDetails {
	
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/** The account owner. */
	private String accountOwner;
	
	/** The bic. */
	private String bic;
	
	/** The iban. */
	private String iban;
	
	/** The credit institution. */
	private String creditInstitution;
	
	/** The owner. */
	@OneToOne(fetch=FetchType.EAGER, mappedBy="supplierPaymentDetails")
	private Supplier owner;

	/**
	 * Instantiates a new payment details.
	 */
	public PaymentDetails() {

	}

	/**
	 * Instantiates a new payment details.
	 *
	 * @param accountOwner the account owner
	 * @param bic the bic
	 * @param iban the iban
	 * @param creditInstitution the credit institution
	 */
	public PaymentDetails( final String accountOwner, final String bic, final String iban,
			final String creditInstitution) {

		this.accountOwner = accountOwner;
		this.bic = bic;
		this.iban = iban;
		this.creditInstitution = creditInstitution;
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
	 * Gets the account owner.
	 *
	 * @return the account owner
	 */
	public String getAccountOwner() {
		return accountOwner;
	}

	/**
	 * Sets the account owner.
	 *
	 * @param accountOwner the new account owner
	 */
	public void setAccountOwner(String accountOwner) {
		this.accountOwner = accountOwner;
	}

	/**
	 * Gets the bic.
	 *
	 * @return the bic
	 */
	public String getBic() {
		return bic;
	}

	/**
	 * Sets the bic.
	 *
	 * @param bic the new bic
	 */
	public void setBic(String bic) {
		this.bic = bic;
	}

	/**
	 * Gets the iban.
	 *
	 * @return the iban
	 */
	public String getIban() {
		return iban;
	}

	/**
	 * Sets the iban.
	 *
	 * @param iban the new iban
	 */
	public void setIban(String iban) {
		this.iban = iban;
	}

	/**
	 * Gets the credit institution.
	 *
	 * @return the credit institution
	 */
	public String getCreditInstitution() {
		return creditInstitution;
	}

	/**
	 * Sets the credit institution.
	 *
	 * @param creditInstitution the new credit institution
	 */
	public void setCreditInstitution(String creditInstitution) {
		this.creditInstitution = creditInstitution;
	}

}

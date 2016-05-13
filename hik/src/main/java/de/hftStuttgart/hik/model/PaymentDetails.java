package de.hftStuttgart.hik.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PaymentDetails{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	private String accountOwner;
	private int bic;
	private int iban;
	private String creditInstitution;
	public PaymentDetails(){
		
	}
	public PaymentDetails(Long id, String accountOwner, int bic, int iban, String creditInstitution) {
		super();
		this.id = id;
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
	public int getBic() {
		return bic;
	}
	public void setBic(int bic) {
		this.bic = bic;
	}
	public int getIban() {
		return iban;
	}
	public void setIban(int iban) {
		this.iban = iban;
	}
	public String getCreditInstitution() {
		return creditInstitution;
	}
	public void setCreditInstitution(String creditInstitution) {
		this.creditInstitution = creditInstitution;
	}
	

}

package de.hftStuttgart.hik;

import org.junit.Test;

import de.hftStuttgart.hik.application.Main;
import de.hftStuttgart.hik.model.Customer;
import de.hftStuttgart.hik.utilities.CustomerUtil;
import junit.framework.TestCase;

public class CustomerTest extends TestCase {

	//ToDo: extra Class mit alle Daten
	
	@Test
	public void testAddCustomer() {
		Customer cus = new Customer(1, "CN", "CPF", "CPL", "CS", 73770, "CC", 1234, "CE", 1, "CCOUNTRY", 123, "HERR","");
		Main main = new Main();
		
		int customerBefore = CustomerUtil.loadAllCustomers().size();
		main.getCustomerData(cus);
		int customerAfter = CustomerUtil.loadAllCustomers().size();
		
		assertEquals(customerAfter, customerBefore+1);
	}
	
	@Test
	public void testAddNoCustomer(){
		Customer cus = new Customer(1, "CN", "CPF", "CPL", "CS", 73770, "CC", 1234, "CE", 1, "CCOUNTRY", 123, "HERR","");
		Main main = new Main();
		
		int customerBefore = CustomerUtil.loadAllCustomers().size();
		main.getCustomerData(null);
		int customerAfter = CustomerUtil.loadAllCustomers().size();
		
		assertEquals(customerAfter, customerBefore);
	}
	
	@Test
	public void testGetCustomerTitel(){
		Customer cus = new Customer(1, "CN", "CPF", "CPL", "CS", 73770, "CC", 1234, "CE", 1, "CCOUNTRY", 123, "Expected","");
		
		CustomerUtil.addCustomer(cus);
		String customerTittel = CustomerUtil.loadAllCustomers().get(cus.getId().intValue()-1).getCustomerTitel();
		
		assertEquals(cus.getCustomerTitel(), customerTittel);
	}
}

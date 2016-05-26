package de.hftStuttgart.hik;

import org.junit.Test;
import org.mockito.Mockito;

import de.hftStuttgart.hik.model.Customer;
import junit.framework.TestCase;


public class CustomerTest extends TestCase {
	
	Customer customer = Mockito.mock(Customer.class);
	
	@Test
	public void testGetCustomerData(){
		customer = new Customer(2, "dede", "dede", "dede", "dede", 12321, "dede", 12321, "dede", 12321, "dede", 12, "edede", "25.04.2016");
		
		assertEquals("dede", customer.getCustomerCompanyName());
	}
	
}

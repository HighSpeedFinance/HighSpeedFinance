package de.hftStuttgart.hik.utilities;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import de.hftStuttgart.hik.TesHelper;
import de.hftStuttgart.hik.model.Customer;
import de.hftStuttgart.hik.model.PostAdress;
import de.hftStuttgart.hik.model.SupplierOrder;

public class CustomerUtilTest {

	private Customer customer = new Customer();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DatabaseConnectionUtil.getEm();
	}

	@Before
	public void setUp() throws Exception {
		customer = TesHelper.customer;

	}

	@Ignore
	@Test
	public void testLoadAllCustomers() {

	}

	@Test
	public void testAddCustomer() {

		CustomerUtil.addCustomer(customer);
		TesHelper.cusList=CustomerUtil.loadAllCustomers();
		Assert.assertTrue(TesHelper.cusList.contains(customer));
	}
	 
	 @Test
	 public void testEditCustomer() {
	 Customer editedCustomer = null;
	
	 CustomerUtil.addCustomer(customer);
	 customer.setCustomerAdressCountry("England");
	 CustomerUtil.editCustomer(customer);
	 TesHelper.cusList=CustomerUtil.loadAllCustomers();
	 if(TesHelper.cusList.contains(customer))
		 editedCustomer=customer;
	 
	 
	 Assert.assertTrue(editedCustomer.getCustomerAdressCountry().equals("England"));
	 }
	 
	 
	 @Ignore
	 @Test
	 public void testDeleteCustomer() {
		 CustomerUtil.deleteCustomer(customer);
			TesHelper.cusList=CustomerUtil.loadAllCustomers();
			Assert.assertTrue(!TesHelper.cusList.contains(customer));
	 }
}

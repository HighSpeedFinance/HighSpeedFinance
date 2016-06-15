package de.hftStuttgart.hik.utilities;

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

public class CustomerUtilTest {

	private static Customer customer = new Customer();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DatabaseConnectionUtil.getEm();
	}

	@Before
	public void setUp() throws Exception {
		if (!TesHelper.cusList.contains(customer)) {
			customer = new Customer();
			customer.setCustomerNumber(TesHelper.customer.getCustomerNumber());
			CustomerUtil.addCustomer(customer);
			TesHelper.cusList.add(customer);
		}

	}

	@After
	public void tearDown() throws Exception {
		if (TesHelper.cusList.contains(customer))
			CustomerUtil.deleteCustomer(customer);
		TesHelper.cusList.clear();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {

	}


	@Test
	public void testAddCustomer() {
		CustomerUtil.addCustomer(customer);
		TesHelper.cusList = CustomerUtil.loadAllCustomers();
		Assert.assertTrue(TesHelper.cusList.contains(customer));
	}


	@Test
	public void testEditCustomer() {
		Customer editedCustomer = null;
		customer.setCustomerContactPersonFirstName("Vedro");
		CustomerUtil.editCustomer(customer);
		List<Customer> customers = CustomerUtil.loadAllCustomers();
		for (Customer cus : customers) {
			if (cus.getId().equals(customer.getId()))
				editedCustomer = cus;
		}

		Assert.assertTrue(editedCustomer.getCustomerContactPersonFirstName().equals(customer.getCustomerContactPersonFirstName()));

	}

	@Test
	public void testDeleteCustomer() {
		CustomerUtil.deleteCustomer(customer);
		TesHelper.cusList = CustomerUtil.loadAllCustomers();
		Assert.assertTrue(!TesHelper.cusList.contains(customer));
	}
}

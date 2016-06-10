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

import de.hftStuttgart.hik.model.Customer;
import de.hftStuttgart.hik.model.PostAdress;
import de.hftStuttgart.hik.model.SupplierOrder;

public class CustomerUtilTest {

	final Customer customer = new Customer();
	final PostAdress customerAdress = new PostAdress();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DatabaseConnectionUtil.getEm();
	}

	@Before
	public void setUp() throws Exception {

		customerAdress.setCity("Stuttgart");
		customerAdress.setCountry("Deutschland");
		customerAdress.setHouseNumber(6);
		customerAdress.setPostIndex(50565);
		customerAdress.setStreet("Koenigsstr");

		customer.setCustomerAdress(customerAdress);

		customer.setAddedDate("10.09.08");

		customer.setCustomerCompanyName("SAP");
		customer.setCustomerContactPersonLastName("Mueller");
		customer.setCustomerContactPersonFirstName("Phillip");
		customer.setCustomerEmail("sap@gmail.com");
		customer.setCustomerFax(123456);
		customer.setCustomerNumber(67889);
		customer.setCustomerPhoneNumber(6783930);
		customer.setCustomerTitel("Titel");
		customer.setDate("14.08.2016");

	}

	@Ignore
	@Test
	public void testLoadAllCustomers() {

	}

	@Test
	public void testAddCustomer() {

		// customerAdress.setCity("Stuttgart");
		// customerAdress.setCountry("Deutschland");
		// customerAdress.setHouseNumber(6);
		// customerAdress.setPostIndex(50565);
		// customerAdress.setStreet("Koenigsstr");
		//
		//
		// customer.setCustomerAdress(customerAdress);
		// customer.setAddedDate("10.09.08");
		// customer.setCustomerCompanyName("SAP");
		// customer.setCustomerContactPersonLastName("Mueller");
		// customer.setCustomerContactPersonFirstName("Phillip");
		// customer.setCustomerEmail("sap@gmail.com");
		// customer.setCustomerFax(123456);
		// customer.setCustomerNumber(67889);
		// customer.setCustomerPhoneNumber(6783930);
		// customer.setCustomerTitel("Titel");
		// customer.setDate("14.08.2016");
		Customer addedCustomer = null;
		CustomerUtil.addCustomer(customer);
		List<Customer> customers = CustomerUtil.loadAllCustomers();
		for (Customer cust : customers) {
			if (cust.getId().equals(customer.getId()))
				addedCustomer = cust;
		}
		Assert.assertTrue(customer.equals(addedCustomer));
	}
	// @Ignore
	// @Test
	// public void testEditCustomer() {
	// Customer editCustomer = null;
	// CustomerUtil.addCustomer(customer);
	// CustomerUtil.editCustomer(customer);
	// List<Customer> customers = CustomerUtil.loadAllCustomers();
	// for (Customer cust : customers )
	// {
	// if(cust.getId().equals(customer.getId()))
	// editCustomer=cust;
	// }
	// Assert.assertTrue(customer.equals(editCustomer));
	// }
	// @Ignore
	// @Test
	// public void testDeleteCustomer() {
	// Customer deletedCustomer = null;
	// CustomerUtil.addCustomer(customer);
	// CustomerUtil.deleteCustomer(customer);
	// List<Customer> customers = CustomerUtil.loadAllCustomers();
	// for (Customer cust : customers )
	// {
	// if(cust.getId().equals(customer.getId()))
	// deletedCustomer=cust;
	// }
	// Assert.assertTrue(customer.equals(deletedCustomer));
	// }
}

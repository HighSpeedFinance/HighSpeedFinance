package de.hftStuttgart.hik.utilities;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.hftStuttgart.hik.model.Customer;
import de.hftStuttgart.hik.model.CustomerOrder;
import de.hftStuttgart.hik.model.Status;
import de.hftStuttgart.hik.model.SupplierOrder;


public class OrderUtilTest {
  CustomerOrder order;
  Customer customer;
	

	@Before
	public void setUp() throws Exception {
		order = new CustomerOrder();
		order.setAmount(2);
		order.setCustomer(customer);
		order.setDescription("Rechner");
		order.setItemNumb(3567);
		order.setOrderNumber(12345);
		order.setStatus(Status.ENABLED);
		order.setSumPrice(1000);
		order.setTax(10.3);
		order.setUnitPrice(500);
		order.setId(2147483648L);
		
		
		
	}

	

	@Test
	public void testLoadAllOrdersWhereStatusOpen() {
		List<CustomerOrder> orders = OrderUtil.loadAllOrdersWhereStatusOpen();
		for (CustomerOrder ord : orders )
		{
			if(!ord.getStatus().equals(Status.PENDING))
				fail("Exeption testing method SupplierOrderUtil.loadAllOrdersWhereStatusOpen()");
		}
	}

	@Test
	public void testLoadAllOrdersWhereStatusSucceeded() {
		List<CustomerOrder> orders = OrderUtil.loadAllOrdersWhereStatusOpen();
			for (CustomerOrder ord : orders )
			{
				if(!ord.getStatus().equals(Status.SUCCEEDED))
					fail("Exeption testing method SupplierOrderUtil.loadAllOrdersWhereStatusOpen()");
			}
		}	
	

	@Test
	public void testAddOrder() {
		CustomerOrder addedOrder = null;
	     OrderUtil.addOrder(order);
		List<CustomerOrder> orders = OrderUtil.loadAllOrders();
		for (CustomerOrder ord : orders )
		{
			if(ord.getId().equals(order.getId())){
				addedOrder=ord;
		}
		Assert.assertTrue(order.equals(addedOrder));
	}}

	@Test
	public void testEditOrder() {
		CustomerOrder editedOrder = null;
		OrderUtil.addOrder(order);
		order.setSumPrice(0);
		OrderUtil.editOrder(order);
		List<CustomerOrder> orders = OrderUtil.loadAllOrders();
		for (CustomerOrder ord : orders )
		{
			if(ord.getId().equals(order.getId())){
				editedOrder=ord;
		}
		Assert.assertTrue(order.getSumPrice()== editedOrder.getSumPrice());
	}
	}

	@Test
	public void testDeleteOrder() {
		CustomerOrder deletedOrder = null;
		OrderUtil.addOrder(order);
		OrderUtil.deleteOrder(order);
		List<CustomerOrder> orders = OrderUtil.loadAllOrders();
		for (CustomerOrder ord : orders )
		{
			if(ord.getId().equals(order.getId())){
				deletedOrder=ord;
		}
		Assert.assertTrue(order.equals(deletedOrder));
	}
	}

}

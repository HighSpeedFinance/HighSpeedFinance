package de.hftStuttgart.hik.utilities;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.hftStuttgart.hik.TesHelper;
import de.hftStuttgart.hik.model.Customer;
import de.hftStuttgart.hik.model.CustomerOrder;
import de.hftStuttgart.hik.model.Status;
import de.hftStuttgart.hik.model.SupplierOrder;


public class OrderUtilTest {
 private CustomerOrder order= new CustomerOrder();
  
 @BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DatabaseConnectionUtil.getEm();
	}

	@Before
	public void setUp() throws Exception {
		order=TesHelper.cOrder;
		
		
		
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
		OrderUtil.addOrder(order);
		TesHelper.cusOList=OrderUtil.loadAllOrders();
		Assert.assertTrue(TesHelper.cusOList.contains(order));
	}

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
		OrderUtil.deleteOrder(order);
		TesHelper.cusOList=OrderUtil.loadAllOrders();
		Assert.assertTrue(!TesHelper.cusOList.contains(order));
	}

}

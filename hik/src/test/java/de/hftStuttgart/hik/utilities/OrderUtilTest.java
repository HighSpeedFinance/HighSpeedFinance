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

import de.hftStuttgart.hik.model.CustomerOrder;
import de.hftStuttgart.hik.model.Status;
import de.hftStuttgart.hik.model.SupplierOrder;

public class OrderUtilTest {
	private static CustomerOrder order = new CustomerOrder();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DatabaseConnectionUtil.getEm();

	}


	@Before
	public void setUp() throws Exception {
		if (!TesHelper.cusOList.contains(order)) {
			order = new CustomerOrder();
			order.setOrderNumber(TesHelper.cOrder.getOrderNumber());
			OrderUtil.addOrder(order);
			TesHelper.cusOList.add(order);
		}
	}

	@After
	public void tearDown() throws Exception {
		if (TesHelper.cusOList.contains(order))
			OrderUtil.deleteOrder(order);
		TesHelper.cusOList.clear();
	}
	
	@AfterClass
	 public static void tearDownAfterClass(){
	 }

	@Test
	public void testLoadAllOrdersWhereStatusOpen() {
		List<CustomerOrder> orders = null;
		Assert.assertNull(orders);
		orders=OrderUtil.loadAllOrdersWhereStatusOpen();
		Assert.assertNotNull(orders);
	}

	@Test
	public void testLoadAllOrdersWhereStatusSucceeded() {
		List<CustomerOrder> orders = null;
		Assert.assertNull(orders);
		orders=OrderUtil.loadAllOrdersWhereStatusSucceeded();
		Assert.assertNotNull(orders);
	}

	@Test
	public void testAddOrder() {
		OrderUtil.addOrder(order);
		TesHelper.cusOList = OrderUtil.loadAllOrders();
		Assert.assertTrue(TesHelper.cusOList.contains(order));
	}

	@Test
	public void testEditOrder() {
		CustomerOrder editedOrder = null;
		order.setSumPrice(0);
		OrderUtil.editOrder(order);
		List<CustomerOrder> orders = OrderUtil.loadAllOrders();
		for (CustomerOrder ord : orders) {
			if (ord.getId().equals(order.getId()))
				editedOrder = ord;
		}
		Assert.assertTrue(order.getSumPrice() == editedOrder.getSumPrice());
	}

	@Test
	public void testDeleteOrder() {
		OrderUtil.deleteOrder(order);
		TesHelper.cusOList = OrderUtil.loadAllOrders();
		Assert.assertTrue(!TesHelper.cusOList.contains(order));
	}
}

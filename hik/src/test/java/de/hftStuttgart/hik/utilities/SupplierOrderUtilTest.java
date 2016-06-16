package de.hftStuttgart.hik.utilities;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.hftStuttgart.hik.TesHelper;
import de.hftStuttgart.hik.model.SupplierOrder;

public class SupplierOrderUtilTest {
	private static SupplierOrder order = new SupplierOrder();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DatabaseConnectionUtil.getEm();

	}

	@Before
	public void setUp() throws Exception {

		if (!TesHelper.supOList.contains(order)) {
			order = new SupplierOrder();
			order.setOrderNumber(TesHelper.sOrder.getOrderNumber());
			SupplierOrderUtil.addOrder(order);
			TesHelper.supOList.add(order);
		}

	}

	@After
	public void tearDown() throws Exception {
		if (TesHelper.supOList.contains(order))
			SupplierOrderUtil.deleteOrder(order);
		TesHelper.supOList.clear();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testLoadAllOrdersWhereStatusOpen() {
		List<SupplierOrder> orders = null;
		Assert.assertNull(orders);
		orders=SupplierOrderUtil.loadAllOrdersWhereStatusOpen();
		Assert.assertNotNull(orders);	
	}

	@Test
	public void testLoadAllOrdersWhereStatusSucceeded() {
		List<SupplierOrder> orders = null;
		Assert.assertNull(orders);
		orders = SupplierOrderUtil.loadAllOrdersWhereStatusSucceeded();
		Assert.assertNotNull(orders);		
	}

	@Test
	public void testAddOrder() {
		SupplierOrderUtil.addOrder(order);
		TesHelper.supOList = SupplierOrderUtil.loadAllOrders();
		Assert.assertTrue(TesHelper.supOList.contains(order));
	}

	@Test
	public void testEditOrder() {
		SupplierOrder editedOrder = null;
		order.setSumPrice(0);
		SupplierOrderUtil.editOrder(order);
		List<SupplierOrder> orders = SupplierOrderUtil.loadAllOrders();
		for (SupplierOrder ord : orders) {
			if (ord.getId().equals(order.getId()))
				editedOrder = ord;
		}
		Assert.assertTrue(order.getSumPrice() == editedOrder.getSumPrice());
	}

	@Test
	public void testDeleteOrder() {
		SupplierOrderUtil.deleteOrder(order);
		TesHelper.supOList = SupplierOrderUtil.loadAllOrders();
		Assert.assertTrue(!TesHelper.supOList.contains(order));
	}

}

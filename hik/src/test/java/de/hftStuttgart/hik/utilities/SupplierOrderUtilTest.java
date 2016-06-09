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

import de.hftStuttgart.hik.model.Status;
import de.hftStuttgart.hik.model.Supplier;
import de.hftStuttgart.hik.model.SupplierOrder;
import javafx.collections.ObservableList;

public class SupplierOrderUtilTest {
private SupplierOrder order;
private Status status;
private Supplier supplier;


	@Before
	public void setUp() throws Exception {
		
	    order = new SupplierOrder();
		order.setOrderNumber(5);
		order.setAmount(4);
		order.setDate("10.02.2016");
		order.setDescription("Rechner fuer Buchhaltung");
		order.setId(2147483648L);
		order.setItemNumb(0603);
		order.setSumPrice(4569);
		order.setStatus(status);
		order.setSupId(65784);
		order.setTax(10.3);
		order.setSupplier(supplier);
		order.setUnitPrice(1000);
		
		
	}
@Ignore
	@After
	public void tearDown() throws Exception {
	}
@Ignore
	@Test
	public void testLoadAllOrdersSupplier() {
		fail("Not yet implemented");
	}
@Ignore
	@Test
	public void testLoadAllOrders() {
		fail("Not yet implemented");
	}

	@Test
	public void testLoadAllOrdersWhereStatusOpen() {
		
		List<SupplierOrder> orders = SupplierOrderUtil.loadAllOrdersWhereStatusOpen();
		for (SupplierOrder ord : orders )
		{
			if(!ord.getStatus().equals(Status.PENDING))
				fail("Exeption testing method SupplierOrderUtil.loadAllOrdersWhereStatusOpen()");
		}
		
	}	
	

	@Test
	public void testLoadAllOrdersWhereStatusSucceeded() {
		List<SupplierOrder> orders = SupplierOrderUtil.loadAllOrdersWhereStatusOpen();
		for (SupplierOrder ord : orders )
		{
			if(!ord.getStatus().equals(Status.SUCCEEDED))
				fail("Exeption testing method SupplierOrderUtil.loadAllOrdersWhereStatusOpen()");
		}
	}	
	

	@Test
	public void testAddOrder() {
		SupplierOrder addedOrder = null;
		SupplierOrderUtil.addOrder(order);
		List<SupplierOrder> orders = SupplierOrderUtil.loadAllOrders();
		for (SupplierOrder ord : orders )
		{
			if(ord.getId().equals(addedOrder.getId()))
				addedOrder=ord;
		}
		Assert.assertTrue(order.equals(addedOrder));
	}

	@Test
	public void testEditOrder() {
		SupplierOrder editedOrder = null;
		SupplierOrderUtil.addOrder(order);
		order.setSumPrice(0);
		SupplierOrderUtil.editOrder(order);
		List<SupplierOrder> orders = SupplierOrderUtil.loadAllOrders();
		for (SupplierOrder ord : orders )
		{
			if(ord.getId().equals(order.getId()))
				editedOrder=ord;
		}
		Assert.assertTrue(order.getSumPrice()== editedOrder.getSumPrice());
	}

	@Test
	public void testDeleteOrder() {
		SupplierOrder deletedOrder = null;
		SupplierOrderUtil.addOrder(order);
		SupplierOrderUtil.deleteOrder(order);
		List<SupplierOrder> orders = SupplierOrderUtil.loadAllOrders();
		for (SupplierOrder ord : orders )
		{
			if(ord.getId().equals(order.getId()))
				deletedOrder=ord;
		}
		Assert.assertTrue(order.equals(deletedOrder));
	}

}

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
import de.hftStuttgart.hik.model.Status;
import de.hftStuttgart.hik.model.Supplier;
import de.hftStuttgart.hik.model.SupplierOrder;
import javafx.collections.ObservableList;

public class SupplierOrderUtilTest {
	private static Supplier supplier = new Supplier();
	private static SupplierOrder order = new SupplierOrder();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DatabaseConnectionUtil.getEm();

		List<Supplier> listSup = SupplierUtil.loadAllSuppliers();
		supplier = listSup.get(0);
		Assert.assertTrue(supplier != null);

	}

	@Before
	public void setUp() throws Exception {

		order = TesHelper.sOrder;

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
		for (SupplierOrder ord : orders) {
			if (!ord.getStatus().equals(Status.PENDING))
				fail("Exeption testing method SupplierOrderUtil.loadAllOrdersWhereStatusOpen()");
		}

	}

	@Test
	public void testLoadAllOrdersWhereStatusSucceeded() {
		List<SupplierOrder> orders = SupplierOrderUtil.loadAllOrdersWhereStatusOpen();
		for (SupplierOrder ord : orders) {
			if (!ord.getStatus().equals(Status.PENDING))
				fail("Exeption testing method SupplierOrderUtil.loadAllOrdersWhereStatusOpen()");
		}
	}

	@Test
	public void testAddOrder() {
		SupplierOrderUtil.addOrder(order);
		TesHelper.supOList=SupplierOrderUtil.loadAllOrders();
		Assert.assertTrue(TesHelper.supOList.contains(order));
	}

	@Test
	public void testEditOrder() {
		SupplierOrder editedOrder = null;
		SupplierOrderUtil.addOrder(order);
		order.setSumPrice(0);
		SupplierOrderUtil.editOrder(order);
		List<SupplierOrder> orders = SupplierOrderUtil.loadAllOrders();
		for (SupplierOrder ord : orders) {
			if (ord.getId().equals(order.getId()))
				editedOrder = ord;
		}
		Assert.assertTrue(order.getSumPrice() == editedOrder.getSumPrice());
	}

	@Ignore
	@Test
	public void testDeleteOrder() {
		SupplierOrderUtil.deleteOrder(order);
		TesHelper.supOList=SupplierOrderUtil.loadAllOrders();
		Assert.assertTrue(!TesHelper.supOList.contains(order));
	}

}

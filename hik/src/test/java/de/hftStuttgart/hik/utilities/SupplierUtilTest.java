package de.hftStuttgart.hik.utilities;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.hftStuttgart.hik.TesHelper;
import de.hftStuttgart.hik.model.PaymentDetails;
import de.hftStuttgart.hik.model.PostAdress;
import de.hftStuttgart.hik.model.Supplier;
import de.hftStuttgart.hik.model.SupplierOrder;
import junit.framework.Assert;

public class SupplierUtilTest {
	private Supplier supplier;
	private List<SupplierOrder> list;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DatabaseConnectionUtil.getEm();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

		supplier = TesHelper.supplier;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddSupplier() {
		SupplierUtil.addSupplier(supplier);
		TesHelper.supList = SupplierUtil.loadAllSuppliers();
		Assert.assertTrue(TesHelper.supList.contains(supplier));
	}

	@Test
	public void testEditSupplier() {
		Supplier editedSup = null;
		SupplierUtil.addSupplier(supplier);
		supplier.setSupplierCompanyName("RechnerExport");
		SupplierUtil.editSupplier(supplier);
		List<Supplier> supList = SupplierUtil.loadAllSuppliers();
		for (Supplier sup : supList) {
			if (sup.equals(supplier))
				editedSup = sup;
		}
		Assert.assertTrue(supplier.getSupplierCompanyName() == editedSup.getSupplierCompanyName());

	}

	@Test
	public void testDeleteSupplier() {

		SupplierUtil.deleteSupplier(supplier);
		TesHelper.supList = SupplierUtil.loadAllSuppliers();
		Assert.assertTrue(!TesHelper.supList.contains(supplier));
	}
}
